
package login.service;


import login.dto.LoginDto;
import login.dto.RegisterDto;
import login.entity.LoginEntity;
import login.exception.UsernotFoundException;
import login.repository.LoginRepository;
import login.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class LoginServiceImpl implements  LoginService {



    private final LoginRepository repo;

    private  final Mailservice mailservice;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    public LoginServiceImpl(LoginRepository repo, Mailservice mailservice) {

        this.repo = repo;
        this.mailservice = mailservice;

    }


    @Override
    public String register(RegisterDto registerDto) {
        LoginEntity loginEntity = new LoginEntity();
        if(registerDto.getPassword() ==null || registerDto.getPassword().isBlank()){
            return "Invalid Password";
        }else if (registerDto.getUsername() ==null || registerDto.getUsername().isBlank()){
            return "Invalid Username";
        }

        loginEntity.setUsername(registerDto.getUsername());
        loginEntity.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        loginEntity.setMail(registerDto.getMail());

        loginEntity.setRoles("ROLE_USER");

        repo.save(loginEntity);

        return "Register Success";
    }

    @Override
    public String login(LoginDto loginDto) {
//

        LoginEntity user = repo.findByusername(loginDto.getUsername())
                .orElseThrow(() -> new UsernotFoundException("User not found"));

        //check acc locked

        if (!user.isAccountstatus()){

            if (!unlockaccount(user)){
                return"Account is locked , try later";
            }
        }
        try{
            if(!passwordEncoder.matches(loginDto.getPassword(),user.getPassword())){
                throw  new RuntimeException("Password is incorrect");
            }
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            loginDto.getUsername(),
//                            loginDto.getPassword()
//                    )
          //  );
            resetattempts(user);
            //return the token ... it menas inside the try we do auth and if success generate token and return it..
//            return jwtUtils.generateToken(
//
//                    user.getUsername(),
//                    user.getRoles()
//            );

            //here we genearate otp
            String otp = String.valueOf((int)(Math.random()*900000)+100000);

            user.setOtp(otp);

            user.setOtpExpiry(LocalDateTime.now().plusMinutes(5));



            repo.save(user);


            mailservice.mailsend(user.getMail(), otp);

            System.out.println("after mail send");
            System.out.println("otp :" + otp);

            return "Otp is sent to user";



        }catch(Exception e){
            e.printStackTrace();
            increaseFailattempts(user);
           throw  new RuntimeException("login failed" + e.getMessage());
        }

    }

    @Override
    public String verifyOtp(String username, String otp) {


        LoginEntity user = repo.findByusername(username)
                .orElseThrow(() -> new UsernotFoundException("User not found"));

        if (user.getOtp()==null){
            throw  new RuntimeException("otp is not generated");
        }

        if(user.getOtpExpiry().isBefore(LocalDateTime.now())){
            throw  new RuntimeException( "otp is expired");
        }

        if(!user.getOtp().equals(otp)){
            throw  new RuntimeException( "otp is not matched");
        }

        user.setOtpExpiry(null);
        user.setOtp(null);

        repo.save(user);



        return jwtUtils.generateToken(

                user.getUsername(),
                user.getRoles()
        );
    }

    @Override
    public String forgotPassword(String username) {

        LoginEntity user = repo.findByusername(username)
                .orElseThrow(() -> new UsernotFoundException("User not found"));


        String reToken = UUID.randomUUID().toString();

        user.setResetToken(reToken);

        user.setTokenExpiry(LocalDateTime.now().plusMinutes(15));

        repo.save(user);

        mailservice.reTokenSend(user.getMail(),reToken);

        System.out.println(reToken);

        return "Reset link generated";
    }

    @Override
    public String resetPassword(String reToken, String newPassword) {


        LoginEntity user = repo.findByResetToken(reToken)
                .orElseThrow(() -> new UsernotFoundException ("Token not Found"));

        if(user.getTokenExpiry().isBefore(LocalDateTime.now())){
            return "Token is expired";
        }


        user.setPassword(passwordEncoder.encode(newPassword));

        user.setResetToken(null);
        user.setTokenExpiry(null);

        repo.save(user);

        return "Password Updated";
    }




    //method for increase fail attempt
    public void increaseFailattempts(LoginEntity user){

        //if user try fail attempts
        int newAttempts = user.getFailAttempts() + 1;

        user.setFailAttempts(newAttempts);

        if(newAttempts >= 3){

            user.setAccountstatus(false);
            user.setLockTime(LocalDateTime.now());
        }

        repo.save(user);

    }

    //method for resetattempt failed
    public void resetattempts(LoginEntity user){


        user.setFailAttempts(0);
        repo.save(user);

    }

    //method for unlock locked account
    public boolean unlockaccount(LoginEntity user){
//set auto unlock after 10 mins
        if (user.getLockTime()==null){
            return false;
        }

        LocalDateTime lockTime = user.getLockTime();
        LocalDateTime now = LocalDateTime.now();

        long minutes = Duration.between(lockTime, now).toMinutes();

        if(minutes >= 10 ){

            user.setFailAttempts(0);
            user.setAccountstatus(true);
            user.setLockTime(null);

            repo.save(user);

            return true;
        }


        return false;
    }





}
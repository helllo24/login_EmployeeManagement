package login.controller;

import login.dto.LoginDto;
import login.dto.RegisterDto;
import login.service.LoginService;
import login.service.SmsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthContoller {


    private final LoginService ser;

    public AuthContoller(LoginService ser, SmsService sms) {
        this.ser = ser;

    }






    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){

        String register = ser.register(registerDto);

        return ResponseEntity.ok(register);

    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){

        String login = ser.login(loginDto);

        return ResponseEntity.ok(login);

    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String username){

        String forgot = ser.forgotPassword(username);
        return ResponseEntity.ok(forgot);
    }

    @PostMapping("/rest-password")
    public ResponseEntity<String> reset(@RequestBody Map<String ,String> req){

        String resetToken = req.get("resetToken");
        String newPassword = req.get("newPassword");

        String s = ser.resetPassword(resetToken, newPassword);
        return ResponseEntity.ok(s);


    }

    @PostMapping("/verify_otp")
    public ResponseEntity<String> verifyOtp(@RequestParam String username,
                                            @RequestParam String otp){
        String verify = ser.verifyOtp(username, otp);
        return ResponseEntity.ok(verify);
    }



}
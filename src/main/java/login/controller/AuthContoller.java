package login.controller;

import jakarta.validation.Valid;
import login.apiResponce.Responce;
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
    public ResponseEntity<Responce<String>> register(@Valid @RequestBody RegisterDto registerDto){

        String register = ser.register(registerDto);

        Responce<String> responce = new Responce<>();
        responce.setSuccess(true);
        responce.setMessage("User Registered Successfully");
        responce.setData(register);

        return ResponseEntity.ok(responce);

    }

    @PostMapping("/login")
    public ResponseEntity<Responce<String>> login(@RequestBody LoginDto loginDto){

        String login = ser.login(loginDto);
        Responce<String> responce = new Responce<>();
        responce.setSuccess(true);
        responce.setMessage("User Login  Successfully");
        responce.setData(login);

        return ResponseEntity.ok(responce);

    }

    @PostMapping("/forgot-password")
    public ResponseEntity<Responce<String>> forgotPassword(@RequestParam String username){

        String forgot = ser.forgotPassword(username);
        Responce<String> responce = new Responce<>();
        responce.setSuccess(true);

        responce.setData(forgot);
        return ResponseEntity.ok(responce);
    }

    @PostMapping("/rest-password")
    public ResponseEntity<Responce<String>> reset(@RequestBody Map<String ,String> req){

        String resetToken = req.get("resetToken");
        String newPassword = req.get("newPassword");

        String s = ser.resetPassword(resetToken, newPassword);
        Responce<String> responce = new Responce<>();
        responce.setSuccess(true);

        responce.setData(s);
        return ResponseEntity.ok(responce);


    }

    @PostMapping("/verify_otp")
    public ResponseEntity<String> verifyOtp(@RequestParam String username,
                                            @RequestParam String otp){
        String verify = ser.verifyOtp(username, otp);
//        Responce<String> responce = new Responce<>();
//        responce.setSuccess(true);
//
//        responce.setData(verify);
        return ResponseEntity.ok(verify);
    }



}
package login.service;


import login.dto.LoginDto;
import login.dto.RegisterDto;

public interface LoginService {


    String register (RegisterDto registerDto);

    String login (LoginDto loginDto);

    String forgotPassword(String username);


    String resetPassword(String token,String newPassword);

    String verifyOtp(String username,String otp);


}

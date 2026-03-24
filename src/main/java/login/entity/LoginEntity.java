
package login.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="login_user")
public class LoginEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;


    private String mail;
    private  String roles;

    private int failAttempts;
    private boolean accountstatus=true;
    private LocalDateTime lockTime;

    @Column(name="resetToken")
    private String resetToken;
    private LocalDateTime tokenExpiry;
    private  String otp;
    private LocalDateTime otpExpiry;

    //get/set
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }
    public String getUsername(){
        return username;
    }
    public void setUsername(String username) {
        this.username=username;
    }

    public String getPassword(){

        return password;
    }

    public void setPassword(String password){
        this.password=password;
    }


    public void setMail(String mail){

        this.mail=mail;
    }

    public String getMail(){
        return mail;
    }


    public void setRoles(String roles){
        this.roles=roles;
    }

    public String getRoles(){

        return roles;
    }


    public void setFailAttempts(int failAttempts){
        this.failAttempts=failAttempts;
    }
    public int getFailAttempts(){
        return failAttempts;
    }

    public void setAccountstatus(boolean accountstatus){

        this.accountstatus=accountstatus;
    }
    public boolean isAccountstatus(){

        return accountstatus;
    }

    public LocalDateTime getLockTime(){
        return  lockTime;
    }

    public void setLockTime(LocalDateTime lockTime){
        this.lockTime=lockTime;

    }

    public void setResetToken(String resetToken){

        this.resetToken = resetToken;
    }

    public String getResetToken(){
        return resetToken;
    }

    public void setTokenExpiry(LocalDateTime tokenExpiry){
        this.tokenExpiry=tokenExpiry;
    }

    public LocalDateTime getTokenExpiry(){
        return tokenExpiry;
    }


    public String getOtp(){
        return otp;
    }
    public void setOtp(String otp){
        this.otp=otp;
    }
    public LocalDateTime getOtpExpiry(){
        return otpExpiry;


    }
    public void setOtpExpiry(LocalDateTime otpExpiry){
        this.otpExpiry=otpExpiry;
    }
}




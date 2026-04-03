
package login.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterDto {

    @NotBlank(message = "Username must be not empty")
    private String username;

    @Size(min = 6,message = "Password must have 6 numbers")
    private String password;

    @Email(message = "must be EmailFormat")
    private String mail;


    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }


    public void setMail(String mail){
        this.mail=mail;
    }

    public String getMail(){
        return mail;
    }


}

package login.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


import java.util.Date;

@Service
public class Mailservice {


    @Autowired
    private JavaMailSender mailSender;

    // Send OTP Mail
    public void mailsend(String mail, String otp) {

        SimpleMailMessage msg = new SimpleMailMessage();

        msg.setFrom("hellojohn4129@gmail.com");   // Verified sender in Brevo
        msg.setTo(mail);
        msg.setSubject("OTP Verification");
        msg.setSentDate(new Date());

        msg.setText(
                "Hello,\n\n" +
                        "Your OTP is: " + otp +
                        "\n\nThis OTP is valid for 5 minutes." +
                        "\nPlease do not share this OTP with anyone." +
                        "\n\nRegards," +
                        "\nLogin System"
        );

        mailSender.send(msg);

        System.out.println("OTP Email Sent Successfully");
    }

    // Send Reset Token Mail
    public void retokensend(String mail, String token) {

        SimpleMailMessage msg = new SimpleMailMessage();

        msg.setFrom("hellojohn4129@gmail.com");   // Verified sender in Brevo
        msg.setTo(mail);
        msg.setSubject("Password Reset");
        msg.setSentDate(new Date());

        msg.setText(
                "Hello,\n\n" +
                        "Your Password Reset Token is:\n\n" +
                        token +
                        "\n\nIf you did not request this, please ignore this email." +
                        "\n\nRegards," +
                        "\nLogin System"
        );

        mailSender.send(msg);

        System.out.println("Reset Email Sent Successfully");
    }

}








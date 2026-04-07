package login.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class Mailservice {

    @Autowired
    private JavaMailSender mailSender;

    public void mailsend(String mail,String otp){


        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("hellojohn4129@gmail.com");

        msg.setTo(mail);

        msg.setSubject("Hi this :  Your otp is  Do not share eeeeeeee ");
        msg.setSentDate(new Date());

        msg.setText("Otp is " + otp);


        mailSender.send(msg);

        System.out.println("Email sent successfully");





    }
    public void reTokenSend(String mail,String retoken){


        SimpleMailMessage msg = new SimpleMailMessage();

        msg.setFrom("hellojohn4129@gmail.com");
        msg.setTo(mail);

        msg.setSubject("Your Retoken is ");
        msg.setSentDate(new Date());


        msg.setText("Retoken is " + retoken);

        mailSender.send(msg);

        System.out.println("Email sent successfully");





    }








}

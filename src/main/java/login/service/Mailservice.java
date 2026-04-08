package login.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Date;

@Service
public class Mailservice {

    @Value("${RESEND_API_KEY}")
    private String apikey;

//    private JavaMailSender mailSender;

    public void mailsend(String mail, String otp) {
sendmailviaOtp(mail,  "Your login otp is :" , otp );

    }

    public void retokensend(String mail, String retoken) {
        sendmailviaOtp(mail, "Your Reset Token" ,retoken);

    }

    private  void sendmailviaOtp(String to, String subject, String content) {

        System.out.println("DEBUG: Using API Key starting with: " + (apikey != null ? apikey.substring(0, 5) : "NULL"));
        String json = "{"
                + "\"from\": \"onboarding@resend.dev\","
                + "\"to\": [\"" + to + "\"],"
                + "\"subject\": \"" + subject + "\","
                + "\"html\": \"<strong>" + content + "</strong>\""
                + "}";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.resend.com/emails"))
                .header("Authorization", "Bearer " + apikey)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenAccept(response -> {
                    System.out.println("Resend Status: " + response.statusCode());
                    System.out.println("Resend Body: " + response.body());
                })
                .exceptionally(e -> {
                    e.printStackTrace();
                    return null;
                });




    }


}


//    public void mailsend(String mail,String otp){
//
//
//        SimpleMailMessage msg = new SimpleMailMessage();
//        msg.setFrom("hellojohn4129@gmail.com");
//
//        msg.setTo(mail);
//
//        msg.setSubject("Hi this :  Your otp is  Do not share eeeeeeee ");
//        msg.setSentDate(new Date());
//
//        msg.setText("Otp is " + otp);
//
//
//        mailSender.send(msg);
//
//        System.out.println("Email sent successfully");
//
//
//
//
//
//    }
//    public void reTokenSend(String mail,String retoken){
//
//
//        SimpleMailMessage msg = new SimpleMailMessage();
//
//        msg.setFrom("hellojohn4129@gmail.com");
//        msg.setTo(mail);
//
//        msg.setSubject("Your Retoken is ");
//        msg.setSentDate(new Date());
//
//
//        msg.setText("Retoken is " + retoken);
//
//        mailSender.send(msg);
//
//        System.out.println("Email sent successfully");
//
//
//
//
//
//    }










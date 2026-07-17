package login.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.*;
import java.util.Date;

@Service
public class Mailservice {


    @Value("${BREVO_API_KEY}")
    private String apiKey;

    private final String API_URL = "https://api.brevo.com/v3/smtp/email";

    public void mailsend(String mail, String otp) {

        sendMail(
                mail,
                "OTP Verification",
                "<h2>Your OTP is: " + otp + "</h2>"
                        + "<p>This OTP is valid for 5 minutes.</p>"
                        + "<p>Please do not share it with anyone.</p>"
        );
    }

    public void retokensend(String mail, String token) {

        sendMail(
                mail,
                "Password Reset",
                "<h2>Password Reset Token</h2>"
                        + "<p>" + token + "</p>"
        );
    }

    private void sendMail(String to, String subject, String html) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("api-key", apiKey);

        Map<String, Object> body = new HashMap<>();

        Map<String, String> sender = new HashMap<>();
        sender.put("name", "Login System");
        sender.put("email", "hellojohn4129@gmail.com");

        body.put("sender", sender);

        List<Map<String, String>> receivers = new ArrayList<>();

        Map<String, String> receiver = new HashMap<>();
        receiver.put("email", to);

        receivers.add(receiver);

        body.put("to", receivers);
        body.put("subject", subject);
        body.put("htmlContent", html);

        HttpEntity<Map<String, Object>> request =
                new HttpEntity<>(body, headers);

        try {

            ResponseEntity<String> response =
                    restTemplate.exchange(
                            API_URL,
                            HttpMethod.POST,
                            request,
                            String.class
                    );

            System.out.println("Status : " + response.getStatusCode());
            System.out.println(response.getBody());

        } catch (Exception e) {

            System.out.println("EMAIL FAILED");
            e.printStackTrace();

        }

    }

}








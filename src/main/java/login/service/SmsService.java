package login.service;

//bjpi djoi wrsv rsal

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class SmsService {

    private final String Api_key ="q89210vHKnmoMCJfiLjOPrA4gleDTFBucsaNyExwRI6GhSUzb7PAmWLjwiNVop41hnGXesDKB6yJvg8H";

    public void sendOtp(String phoneno,String otp) {

        try {

            String url = "https://www.fast2sms.com/dev/bulkV2";

            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.set("authorization", Api_key);
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("route","v3");
            requestBody.put("message","Your otp is :" + otp);
            requestBody.put("language","english");
            requestBody.put("flash",0);
            requestBody.put("numbers",phoneno);

            HttpEntity<Map<String,Object>> entity = new HttpEntity<>(requestBody,headers);

            ResponseEntity<String> response =
                    restTemplate.postForEntity(url,entity,String.class);

            System.out.println("SMS response: " + response.getBody());

        } catch (Exception e) {

            System.out.println("SMS FAILED ❌");
            e.printStackTrace(); // 🔥 real error inga theriyum
        }
    }








}

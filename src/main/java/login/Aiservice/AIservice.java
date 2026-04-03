package login.Aiservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class AIservice {

@Value("${gemini.api.key}")
    private String apiKey;

    // Use v1beta and ensure the colon (:) is before generateContent
    private static final String baseUrl = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=";


    public String AiService(String question) {
        //set restemplete for communicate extranal
        RestTemplate restTemplate = new RestTemplate();

        //headers
        //gemini no needs authorization  so onlu need content type , because it tell
        // how data to be must like JSON format
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        //nested box ( Russian doll method ) noting but inside - > inside ->
        //like a box
        Map<String,Object> box1 = new HashMap<>();
        box1.put("text",question);

        //that box inside the another one
        Map<String,Object> box2 = new HashMap<>();
        box2.put("parts",List.of(box1));

        //finally create a body ( containner )
        Map<String , Object> body = new HashMap<>();
        body.put("contents",List.of(box2));

        //combine the header and body
        HttpEntity<Map<String,Object>> request = new HttpEntity<>(body , headers);
        try {

            // we combine the url and key and request
            ResponseEntity<Map> responce =restTemplate.postForEntity(baseUrl+apiKey,request, Map.class);
            Map<String,Object> responcebody = responce.getBody();

            //important code (like find the final answer extract the amnser like Digging)
            if (responcebody !=null & responcebody.containsKey("candidates")){
                List<Map<String,Object>> Answers = (List<Map<String, Object>>) responcebody.get("candidates");
                Map<String ,Object> firstAnswer =  Answers.get(0);
                Map<String ,Object> content = (Map<String, Object>) firstAnswer.get("content");
                List<Map<String,Object>> finalans = (List<Map<String, Object>>) content.get("parts");


                return finalans.get(0).get("text").toString();



            }
        }catch (Exception e){
            return "Error " + e.getMessage();
        }


        return "No responce from Api";

    }



    }






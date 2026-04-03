package login.controller;


import login.Aiservice.AIservice;
import login.dto.AiDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/aI")
public class AIController {

@Autowired
    private  AIservice aIservice;



    @PostMapping("/ask")
    public ResponseEntity<String> askQuestion(@RequestBody AiDto aiDto){

        String answer = aIservice.AiService(aiDto.getQuestion());

        return ResponseEntity.ok(answer);






    }
}

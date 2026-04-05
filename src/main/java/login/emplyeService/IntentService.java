package login.emplyeService;

import org.springframework.stereotype.Service;

@Service
public class IntentService {

    public IntentType intentDetect(String question) {

        question = question.toLowerCase();

        if (question.contains("how many ") || question.contains("count")) {

            //action
            return IntentType.COUNT;
        } else if (question.contains("list") || question.contains("show all ")) {
            //action
            return IntentType.LIST;


        } else {

            //action
            return IntentType.PERSON;
        }
    }
}

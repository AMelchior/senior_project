package hello;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class VoiceController {

    private static final String template = "%s";
    private final AtomicLong counter = new AtomicLong();
    private String output;

    @RequestMapping("/voice")
    public String voice(@RequestParam(value="content", defaultValue="") String content) {
        try {
            String api = "https://2vijq0kx1l.execute-api.us-east-1.amazonaws.com/api/recognize?apikey=b7a8965334ca120e207675cf2af9fa89a058335d26eceb8061138c91f2865e56";
            String arguments = "--upload-file ./voice.wav";
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.add("Content-Type", " \"application/octet-stream\"");
            HttpEntity<String> entity = new HttpEntity<>(arguments, headers);

            ResponseEntity<String> r = restTemplate.exchange(api, HttpMethod.GET, entity, String.class);
            output = r.getBody();
            return output;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw (ex);
        }
    }
}
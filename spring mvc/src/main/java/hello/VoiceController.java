package hello;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
public class VoiceController {

    private String output;
    private Voice_recog vr = new Voice_recog("");


    //localhost8080/voice
    @RequestMapping("/voice")
    public String voice(@RequestParam(value="content", defaultValue="") String content) {
        try {
            String api = Properties.api;
            FileSystemResource fsr = vr.getFileSystemResource();
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            HttpEntity<FileSystemResource> entity = new HttpEntity<>(fsr, headers);

           System.out.println(api + "\n" +
                              entity);
            ResponseEntity<String> r = restTemplate.postForEntity(api, entity, String.class);

            output = r.getBody();
            vr.setContent(output);
            System.out.println(output);
            return output;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw (ex);
        }
    }

    @RequestMapping(value = "/words", method = RequestMethod.GET)
    public ResponseEntity<String[]> listAllWords() {
        String[] words = vr.getWords();
        if (words != null &&words.length == 0) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<String[]>(words, HttpStatus.OK);
    }
    @RequestMapping(value = "/times", method = RequestMethod.GET)
    public ResponseEntity<List<Long>> listAllTimes() {
        List<Long> startTimes = vr.getStartTimes();
        if (startTimes.size() == 0) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Long>>(startTimes, HttpStatus.OK);
    }

    @RequestMapping(value = "/content", method = RequestMethod.GET)
    public ResponseEntity<String> listAllContent() {
        String words = vr.getContent();
        if (words.length() == 0) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<String>(words, HttpStatus.OK);
    }
}
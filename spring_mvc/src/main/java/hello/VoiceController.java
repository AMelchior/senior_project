package hello;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class VoiceController {

    File file = new File(Properties.audio);

    private static final String template = "%s";
    private final AtomicLong counter = new AtomicLong();
    private String output;
/*

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(100000);
        return multipartResolver;
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public String submit(@RequestParam("file") MultipartFile file, ModelMap modelMap) {
        modelMap.addAttribute("file", file);
        return "fileUploadView";
    }
*/
    @RequestMapping("/voice")
    public String voice(@RequestParam(value="content", defaultValue="") String content) {
        try {
            String api = Properties.api;

            String arguments = "--upload-file "+ Properties.audio;
            RestTemplate restTemplate = new RestTemplate();
           /*
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.add("Content-Type", "application/octet-stream");
            HttpEntity<String> entity = new HttpEntity<>(arguments, headers);
            */
            HttpHeaders headers1 = new HttpHeaders();
            headers1.setContentType(MediaType.MULTIPART_FORM_DATA);
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("file", Properties.audio);
            HttpEntity<MultiValueMap<String,Object>> requestEntity = new HttpEntity<>(body, headers1);
            //System.out.println(requestEntity);
            System.out.println( api + "\n" +
                    headers1 + "\n" +
                    body+ "\n" );
            //ResponseEntity<String> r = restTemplate.exchange(api, HttpMethod.GET, entity, String.class);
            //ResponseEntity<String> r = restTemplate.postForEntity(api, entity, String.class);
            ResponseEntity<String> r = restTemplate.postForEntity(api, requestEntity, String.class);

            output = r.getBody();
            System.out.println(output);
            return output;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw (ex);
        }
    }
}
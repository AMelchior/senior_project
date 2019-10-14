package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public Object run() throws Exception {

        try {
            String api = "https://api.github.com/";//"https://2vijq0kx1l.execute-api.us-east-1.amazonaws.com/api/recognize?apikey=b7a8965334ca120e207675cf2af9fa89a058335d26eceb8061138c91f2865e56";
            String arguments = "--upload-file ./voice.wav";
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.add("user-agent", "Content-Type:application/octet-stream Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
            HttpEntity<String> entity = new HttpEntity<>(arguments, headers);

            return restTemplate.exchange(api, HttpMethod.GET, entity, Object.class);

        /*return args -> {
            Voice_recog v = restTemplate.getForObject(
                    api,
                    Voice_recog.class,
                    "");
            log.info(v.toString());
        };*/

        } catch(Exception ex) {

            ex.printStackTrace();
            throw(ex);
        }
    }


}
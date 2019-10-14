/*package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

public abstract class MyCommandLineRunner implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    public MyCommandLineRunner() {}

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {
            Voice_recog v = restTemplate.getForObject(
                    "",
                    Voice_recog.class);
            log.info(v.toString());
        };
    }
}
*/
package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class VoiceController {

    private static final String template = "%s";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/voice_recog")
    public Voice_recog voice_recog(@RequestParam(value="content", defaultValue="") String content) {
        return new Voice_recog(counter.incrementAndGet(), String.format(template, content));
    }
}
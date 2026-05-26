package spring.learning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LearningController {

    @GetMapping("/")
    public String hello() {
        return "Hello World";
    }
}
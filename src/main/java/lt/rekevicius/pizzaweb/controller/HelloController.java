package lt.rekevicius.pizzaweb.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Mindaugas on 2017-03-19.
 */
@RestController
public class HelloController {
    @RequestMapping("/")
    public String index(){
        return "Greetings from Spring Boot!";
    }
}

package de.adesso.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/HelloWorld")
    public String HelloWorld() {
        return "<h1>Hello World !</h1>";
    }


}

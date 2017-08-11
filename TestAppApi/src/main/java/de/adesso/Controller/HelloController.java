package de.adesso.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HelloController {

@RequestMapping("/HelloWorld")
public String HelloWorld() {
return "<h1>Hello World !</h1>";
}

    @GetMapping("/")
    @ResponseBody
    public String home(HttpServletRequest request){
        return "index";
    }

}

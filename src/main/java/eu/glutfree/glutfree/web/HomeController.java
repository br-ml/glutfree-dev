package eu.glutfree.glutfree.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/home")
    public String home() {
        return "index";
    }

    @GetMapping("/blog")
    public String blog() {
        return "coming-soon";
    }

    @GetMapping("/aboutus")
    public String aboutus() {
        return "aboutus";
    }
}
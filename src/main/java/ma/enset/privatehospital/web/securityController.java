package ma.enset.privatehospital.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class securityController {

    @GetMapping("/403")
    public String notAuth(){
        return "403";
    }
}

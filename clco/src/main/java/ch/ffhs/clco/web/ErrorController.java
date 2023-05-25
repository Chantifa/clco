package ch.ffhs.clco.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController implements Errors {

    @Override
    @RequestMapping("/error")
    public String handleError() {
        // Return the path to your custom error page
        return "error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}

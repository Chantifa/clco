package ch.ffhs.clco.web;

import org.springframework.web.bind.annotation.RequestMapping;

public interface Errors extends org.springframework.boot.web.servlet.error.ErrorController {
    @RequestMapping("/error")
    String handleError();

    String getErrorPath();
}

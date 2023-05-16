package ch.ffhs.clco.web;

import ch.ffhs.clco.service.ClcoService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClcoController {

    private ClcoService clcoService;

    @GetMapping("/data")
    public String getData() {
        return clcoService.getData();
    }
}
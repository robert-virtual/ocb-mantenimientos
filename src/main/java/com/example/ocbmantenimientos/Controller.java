package com.example.ocbmantenimientos;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/execute")
public class Controller {
    @PostMapping
    public String execute() {
        return "executed";
    }
}

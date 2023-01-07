package com.example.medium.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("k6")
public class K6Controller {

    @GetMapping
    public String action() {
        log.info("working to k6 controller");
        return "ok";
    }
}

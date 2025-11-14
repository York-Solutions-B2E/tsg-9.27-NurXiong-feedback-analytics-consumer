package com.yorksolutions.tsg927nurxiongfeedbackanalyticsconsumer.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/")
public class FeedbackController {

//    http://localhost:8081/swagger-ui/index.html
    @Operation (summary = "Check consumer health")
    @GetMapping("/health")
    public ResponseEntity<String> getHealth() {
        return ResponseEntity.ok().body("Consumer is healthy");
    }
}

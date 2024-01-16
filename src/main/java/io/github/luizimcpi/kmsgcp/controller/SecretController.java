package io.github.luizimcpi.kmsgcp.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.luizimcpi.kmsgcp.controller.request.SecretRequest;
import io.github.luizimcpi.kmsgcp.service.SecretService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/secrets")
@Slf4j
public class SecretController {
    
    private final SecretService secretService;

    public SecretController(SecretService secretService) {
        this.secretService = secretService;
    }
    
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody SecretRequest secretRequest) {
        try {
            secretService.create(secretRequest);
        } catch (Exception e) {
           log.error("Error while create", e.getMessage());
        }
        return ResponseEntity.created(URI.create("/keys")).build();
    }
}

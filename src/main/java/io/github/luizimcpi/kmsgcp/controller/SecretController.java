package io.github.luizimcpi.kmsgcp.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.luizimcpi.kmsgcp.controller.request.SecretRequest;
import io.github.luizimcpi.kmsgcp.controller.request.SecretResponse;
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

    @GetMapping("/{partnerId}/{key}")
    public ResponseEntity<SecretResponse> findByPartnerAndKey(@PathVariable String partnerId, @PathVariable String key) {
        Optional<SecretResponse> optResponse = secretService.findByPartnerAndKey(partnerId, key);
        if(optResponse.isPresent()){
            return ResponseEntity.ok(optResponse.get());
        }
        return ResponseEntity.notFound().build();
    }
}

package io.github.luizimcpi.kmsgcp.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import io.github.luizimcpi.kmsgcp.controller.request.SecretRequest;
import io.github.luizimcpi.kmsgcp.service.gcp.crypto.DecryptSymmetricService;
import io.github.luizimcpi.kmsgcp.service.gcp.crypto.EncryptSymmetricService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SecretService {
    
    private final EncryptSymmetricService encryptSymmetricService;
    private final DecryptSymmetricService decryptSymmetricService;

    public SecretService(EncryptSymmetricService encryptSymmetricService, DecryptSymmetricService decryptSymmetricService) {
        this.encryptSymmetricService = encryptSymmetricService;
        this.decryptSymmetricService = decryptSymmetricService;
    }


    public void create(SecretRequest request) throws IOException{
        try {
            byte[] encryptedKey = encryptSymmetricService.encryptSymmetric(request.key());
            byte[] encryptedValue = encryptSymmetricService.encryptSymmetric(request.value());
            // decryptSymmetricService.decryptSymmetric(encryptedKey);
            // decryptSymmetricService.decryptSymmetric(encryptedValue);
        } catch (IOException e) {
           log.error("Can't encrypt secret: {}", e.getMessage());
           throw e;
        }
    }
}

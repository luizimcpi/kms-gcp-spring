package io.github.luizimcpi.kmsgcp.service;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import io.github.luizimcpi.kmsgcp.controller.request.SecretRequest;
import io.github.luizimcpi.kmsgcp.controller.request.SecretResponse;
import io.github.luizimcpi.kmsgcp.repository.SecretRepository;
import io.github.luizimcpi.kmsgcp.repository.mongo.documents.Secret;
import io.github.luizimcpi.kmsgcp.service.gcp.crypto.DecryptSymmetricService;
import io.github.luizimcpi.kmsgcp.service.gcp.crypto.EncryptSymmetricService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SecretService {
    
    private final EncryptSymmetricService encryptSymmetricService;
    private final DecryptSymmetricService decryptSymmetricService;
    private final SecretRepository secretRepository;

    public SecretService(
        EncryptSymmetricService encryptSymmetricService, 
        DecryptSymmetricService decryptSymmetricService, 
        SecretRepository secretRepository) {
        this.encryptSymmetricService = encryptSymmetricService;
        this.decryptSymmetricService = decryptSymmetricService;
        this.secretRepository = secretRepository;
    }


    public void create(final SecretRequest request) throws Exception {
        try {
            byte[] encryptedValue = encryptSymmetricService.encryptSymmetric(request.value());

            Secret secret = new Secret();
            secret.setPartnerId(request.partnerId());
            secret.setKey(request.key());
            secret.setValue(encryptedValue);
            // decryptSymmetricService.decryptSymmetric(encryptedKey);
            // decryptSymmetricService.decryptSymmetric(encryptedValue);
            secretRepository.save(secret);
        } catch (Exception e) {
           log.error("Can't create secret: {}", e.getMessage());
           throw e;
        }
    }

    public Optional<SecretResponse> findByPartnerAndKey(final String partnerId, final String key) {
        try {
            Secret secret = secretRepository.findByPartnerIdAndKey(partnerId, key);
            if(Objects.nonNull(secret)){
                final String decryptedValue = decryptSymmetricService.decryptSymmetric(secret.getValue());
                return Optional.of(new SecretResponse(secret.getPartnerId(), secret.getKey(), decryptedValue));
            }
        } catch (Exception e) {
            log.error("Can't recover secret: {}", e.getMessage());
            return Optional.empty();
        }
        return Optional.empty();
    }
}

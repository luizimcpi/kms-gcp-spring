package io.github.luizimcpi.kmsgcp.service.gcp.crypto;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.google.cloud.kms.v1.CryptoKeyName;
import com.google.cloud.kms.v1.EncryptResponse;
import com.google.cloud.kms.v1.KeyManagementServiceClient;
import com.google.protobuf.ByteString;

@Component
public class EncryptSymmetricService {
  public byte[] encryptSymmetric(final String textToEncrypt) throws IOException {
  
    String projectId = "peppy-sensor-411115";
    String locationId = "global";
    String keyRingId = "spring-keyring";
    String keyId = "symetric-encryption-key";
    
    return encryptSymmetric(projectId, locationId, keyRingId, keyId, textToEncrypt);
  }

  // Encrypt data with a given key.
  public byte[] encryptSymmetric(
      String projectId, String locationId, String keyRingId, String keyId, String plaintext)
      throws IOException {
    
    try (KeyManagementServiceClient client = KeyManagementServiceClient.create()) {
      CryptoKeyName keyVersionName = CryptoKeyName.of(projectId, locationId, keyRingId, keyId);

      // Encrypt the plaintext.
      EncryptResponse response = client.encrypt(keyVersionName, ByteString.copyFromUtf8(plaintext));
      return response.getCiphertext().toByteArray();
    }
  }
}

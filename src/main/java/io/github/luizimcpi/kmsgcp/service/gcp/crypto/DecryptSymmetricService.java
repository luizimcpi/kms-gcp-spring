package io.github.luizimcpi.kmsgcp.service.gcp.crypto;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.google.cloud.kms.v1.CryptoKeyName;
import com.google.cloud.kms.v1.DecryptResponse;
import com.google.cloud.kms.v1.KeyManagementServiceClient;
import com.google.protobuf.ByteString;

@Service
public class DecryptSymmetricService {
    
    public String decryptSymmetric(byte[] ciphertext) throws IOException {
        String projectId = "peppy-sensor-411115";
        String locationId = "global";
        String keyRingId = "spring-keyring";
        String keyId = "symetric-encryption-key";
        return decryptSymmetric(projectId, locationId, keyRingId, keyId, ciphertext);
    }

  // Decrypt data that was encrypted using a symmetric key.
  public String decryptSymmetric(
      String projectId, String locationId, String keyRingId, String keyId, byte[] ciphertext)
      throws IOException {
    // Initialize client that will be used to send requests. This client only
    // needs to be created once, and can be reused for multiple requests. After
    // completing all of your requests, call the "close" method on the client to
    // safely clean up any remaining background resources.
    try (KeyManagementServiceClient client = KeyManagementServiceClient.create()) {
      // Build the key version name from the project, location, key ring, and
      // key.
      CryptoKeyName keyName = CryptoKeyName.of(projectId, locationId, keyRingId, keyId);

      // Decrypt the response.
      DecryptResponse response = client.decrypt(keyName, ByteString.copyFrom(ciphertext));
      return response.getPlaintext().toStringUtf8();
    }
  }
}

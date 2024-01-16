package io.github.luizimcpi.kmsgcp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import io.github.luizimcpi.kmsgcp.repository.mongo.documents.Secret;

public interface SecretRepository extends MongoRepository<Secret, String>  {
    Secret findByPartnerIdAndKey(String partnerId, String key);
} 

package io.github.luizimcpi.kmsgcp.repository.mongo.documents;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("secret")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Secret {

    @Id
    private String id;

    private String partnerId;

    private String key;

    private byte[] value;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

}

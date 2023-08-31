package com.roadside.backend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

@Data
@Document
public class User {
    // The @Id annotation marks the field as the document's identifier
    @Id
    private String id;

    // Fields representing user data
    private String username;
    private String email;
    private String password;
}

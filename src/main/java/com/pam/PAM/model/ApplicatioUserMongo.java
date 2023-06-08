package com.pam.PAM.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "applicationUsers")
public class ApplicatioUserMongo {
    @Id
    private String userID;
    private String username;
    private String password;
    private String email;
    private String role;
    private String nic;
}

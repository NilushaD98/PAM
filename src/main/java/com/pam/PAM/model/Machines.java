package com.pam.PAM.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor@NoArgsConstructor@Data
@Document(collection = "machines")
public class Machines {
    @Id
    private String machineID;
    @Indexed(unique = true)
    private String machineIP;
    private String username;
    private String password;
    private String oSystem;
}

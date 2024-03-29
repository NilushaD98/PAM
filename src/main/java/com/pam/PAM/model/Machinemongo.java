package com.pam.PAM.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "machines")
public class Machinemongo {
    @Id
    private String machineID;
    private String machineIP;
    private String machineName;
    private String username;
    private String password;
    private String machineOS;



}

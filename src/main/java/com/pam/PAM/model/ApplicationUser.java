package com.pam.PAM.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@AllArgsConstructor@NoArgsConstructor@Data
@Entity
public class ApplicationUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userID;
    private String username;
    private String password;
    private String email;
    private String role;
    private String nic;

    public ApplicationUser(String username, String password, String email, String role, String nic) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.nic = nic;
    }
}

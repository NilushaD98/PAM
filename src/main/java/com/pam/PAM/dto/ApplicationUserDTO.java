package com.pam.PAM.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApplicationUserDTO {

    private String userID;
    private String username;
    private String password;
    private String email;
    private String role;
    private String nic;

}

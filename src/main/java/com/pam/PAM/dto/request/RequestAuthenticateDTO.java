package com.pam.PAM.dto.request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestAuthenticateDTO {
    private String username;
    private String password;
    private String nic;
    private String machineName;
}

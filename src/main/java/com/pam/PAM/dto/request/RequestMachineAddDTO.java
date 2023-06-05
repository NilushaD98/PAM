package com.pam.PAM.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor@NoArgsConstructor@Data
public class RequestMachineAddDTO {
    private String machineID;
    private String machineIP;
    private String username;
    private String password;
}

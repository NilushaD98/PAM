package com.pam.PAM.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestMachineAddDTO {

    private String machineIP;
    private String machineName;
    private String username;
    private String password;
    private String machineOS;
}

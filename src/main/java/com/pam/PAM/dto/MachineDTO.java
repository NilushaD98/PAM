package com.pam.PAM.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MachineDTO {

    private String machineID;
    private String machineIP;
    private String machineName;
    private String username;
    private String password;
    private String machineOS;

}

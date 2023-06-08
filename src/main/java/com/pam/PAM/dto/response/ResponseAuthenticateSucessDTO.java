package com.pam.PAM.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseAuthenticateSucessDTO {
    private String redirect_status;
    private String access_token;
    private String machineIP;
    private String machineUsername;
    private String machinePassword;
    private String oSystem;

}

package com.pam.PAM.service;

import com.pam.PAM.dto.response.ResponseMachineNameDTO;
import com.pam.PAM.dto.response.ResponseUsernamesDTO;

import java.util.List;

public interface UserService {
    public List<ResponseUsernamesDTO> findAllUsernames();

    List<ResponseMachineNameDTO> findMachineName();
}

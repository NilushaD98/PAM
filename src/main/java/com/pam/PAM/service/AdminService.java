package com.pam.PAM.service;

import com.pam.PAM.dto.ApplicationUserDTO;
import com.pam.PAM.dto.MachineDTO;
import com.pam.PAM.dto.request.RequestAddUserDTO;
import com.pam.PAM.dto.request.RequestMachineAddDTO;
import com.pam.PAM.model.ApplicatioUserMongo;
import com.pam.PAM.model.Machinemongo;

import java.util.List;


public interface AdminService {
    String addUser(RequestAddUserDTO requestAddUserDTO);

    List<ApplicatioUserMongo> findAllUsers();

    List<ApplicatioUserMongo> findByUsername(String username);

    String updateUser(ApplicationUserDTO requestAddUserDTO);
    String deleteUser(String username);

    String addMachine(RequestMachineAddDTO requestMachineAddDTO);

    List<Machinemongo> getAllMachines();

    String updateMAchine(MachineDTO machineDTO);

    String deleteMAchineByID(String machineName);
}

package com.pam.PAM.service;

import com.pam.PAM.dto.ApplicationUserDTO;
import com.pam.PAM.dto.request.RequestAddUserDTO;
import com.pam.PAM.model.ApplicationUser;

import java.util.List;


public interface AdminService {
    String addUser(RequestAddUserDTO requestAddUserDTO);

    List<ApplicationUser> findAllUsers();

   ApplicationUser findByUsername(String username);
    ApplicationUser findUserByUserEmail(String email);
    String updateUser(ApplicationUserDTO requestAddUserDTO);
    String deleteUser(String username);
}

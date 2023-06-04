package com.pam.PAM.service.serviceIMPL;

import com.pam.PAM.dto.request.RequestAddUserDTO;
import com.pam.PAM.model.ApplicationUser;
import com.pam.PAM.repo.UserRepo;
import com.pam.PAM.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AdminServiceIMPL implements AdminService {
    @Autowired
    private UserRepo userRepo;


    @Override
    public String addUser(RequestAddUserDTO requestAddUserDTO) {
        ApplicationUser applicationUser = new ApplicationUser(
                UUID.randomUUID().toString().split("-")[0],
                requestAddUserDTO.getUsername(),
                requestAddUserDTO.getPassword(),
                requestAddUserDTO.getEmail(),
                requestAddUserDTO.getRole()

        );
        userRepo.insert(applicationUser);
        System.out.println("saved");
        return applicationUser.getUsername()+" saved";
    }

    public List<ApplicationUser> findAllUsers(){
        userRepo.findAll();
        System.out.println(userRepo.findAll());
        return userRepo.findAll();
    }

    public ApplicationUser findByUsername(String username){

        List<ApplicationUser> users = userRepo.findByUsername(username);
        return users.get(0);
    }

    public ApplicationUser findUserByUserEmail(String email){
        return userRepo.findByEmailEquals(email);
    }
//    public String updateUser(RequestAddUserDTO requestAddUserDTO){
//        ApplicationUser applicationUser = userRepo.findByUsername(requestAddUserDTO.getUsername());
//        applicationUser.setUsername(requestAddUserDTO.getUsername());
//        applicationUser.setEmail(requestAddUserDTO.getEmail());
//        applicationUser.setPassword(requestAddUserDTO.getPassword());
//        applicationUser.setRole(requestAddUserDTO.getRole());
//
//        userRepo.save(applicationUser);
//        return applicationUser.getUsername()+" updated.";
//    }
    public String deleteUser(String username){
        return userRepo.deleteByUsernameEquals(username)+" deleted.";
    }
}

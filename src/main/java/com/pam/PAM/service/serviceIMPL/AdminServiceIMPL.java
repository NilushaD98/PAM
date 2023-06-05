package com.pam.PAM.service.serviceIMPL;

import com.pam.PAM.dto.ApplicationUserDTO;
import com.pam.PAM.dto.request.RequestAddUserDTO;
import com.pam.PAM.dto.response.ResponseUsernamesDTO;
import com.pam.PAM.exceptions.UserNameAlreadyTakenException;
import com.pam.PAM.model.ApplicationUser;
import com.pam.PAM.repo.UserRepo;
import com.pam.PAM.service.AdminService;
import com.pam.PAM.util.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AdminServiceIMPL implements AdminService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserMapper userMapper;


    @Override
    public String addUser(RequestAddUserDTO requestAddUserDTO) {
        if(userRepo.findByUsername(requestAddUserDTO.getUsername()).isEmpty()){
            ApplicationUser applicationUser = new ApplicationUser(
                    UUID.randomUUID().toString().split("-")[0],
                    requestAddUserDTO.getUsername(),
                    requestAddUserDTO.getPassword(),
                    requestAddUserDTO.getEmail(),
                    requestAddUserDTO.getRole()
            );
            userRepo.insert(applicationUser);
            return applicationUser.getUsername()+" saved";
        }
        else {
            throw new UserNameAlreadyTakenException();
        }
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
    public String updateUser(ApplicationUserDTO requestAddUserDTO){
        Optional<ApplicationUser> applicationUser1= userRepo.findById(requestAddUserDTO.getUserID());
        ApplicationUser applicationUser= applicationUser1.get();
        applicationUser.setUsername(requestAddUserDTO.getUsername());
        applicationUser.setEmail(requestAddUserDTO.getEmail());
        applicationUser.setPassword(requestAddUserDTO.getPassword());
        applicationUser.setRole(requestAddUserDTO.getRole());
        userRepo.save(applicationUser);
        return applicationUser.getUsername()+" updated.";
    }
    public String deleteUser(String username){
        return userRepo.deleteByUsernameEquals(username)+" deleted.";
    }
}

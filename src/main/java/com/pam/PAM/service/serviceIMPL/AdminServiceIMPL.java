package com.pam.PAM.service.serviceIMPL;

import com.pam.PAM.dto.ApplicationUserDTO;
import com.pam.PAM.dto.MachineDTO;
import com.pam.PAM.dto.request.RequestAddUserDTO;
import com.pam.PAM.dto.request.RequestMachineAddDTO;
import com.pam.PAM.exceptions.MachineNotFoundExcepyion;
import com.pam.PAM.exceptions.UserNameAlreadyTakenException;
import com.pam.PAM.exceptions.UserNotFoundException;
import com.pam.PAM.model.ApplicationUser;
import com.pam.PAM.model.Machines;
import com.pam.PAM.repo.MachineRepo;
import com.pam.PAM.repo.UserRepo;
import com.pam.PAM.service.AdminService;
import com.pam.PAM.util.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AdminServiceIMPL implements AdminService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private MachineRepo machineRepo;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BCryptPasswordEncoder cryptPasswordEncoder;

    @Override
    public String addUser(RequestAddUserDTO requestAddUserDTO) {


        ApplicationUser applicationUser = new ApplicationUser(
                requestAddUserDTO.getUsername(),
                cryptPasswordEncoder.encode(requestAddUserDTO.getPassword()),
                requestAddUserDTO.getEmail(),
                requestAddUserDTO.getRole(),
                cryptPasswordEncoder.encode(requestAddUserDTO.getNic())
        );
        userRepo.save(applicationUser);
        return applicationUser.getUsername() + " saved";


}

    public List<ApplicationUser> findAllUsers(){
        userRepo.findAll();
        System.out.println(userRepo.findAll());
        return userRepo.findAll();
    }
    public ApplicationUser findByUsername(String username){

        ApplicationUser users = userRepo.findApplicationUserByUsernameEquals(username);
        return users;
    }
    public ApplicationUser findUserByUserEmail(String email){
        return userRepo.findApplicationUserByEmailEquals(email);
    }
    public String updateUser(ApplicationUserDTO requestAddUserDTO){
        Optional<ApplicationUser> byUsername = userRepo.findById(requestAddUserDTO.getUserID());
        if (byUsername.isPresent()){
            ApplicationUser applicationUser= byUsername.get();
            applicationUser.setUsername(requestAddUserDTO.getUsername());
            applicationUser.setEmail(requestAddUserDTO.getEmail());
            applicationUser.setPassword(requestAddUserDTO.getPassword());
            applicationUser.setRole(requestAddUserDTO.getRole());
            applicationUser.setNic(requestAddUserDTO.getNic());
            userRepo.save(applicationUser);
            return applicationUser.getUsername()+" updated.";
        }else {
            throw new UserNotFoundException();
        }

    }
    public String deleteUser(String username){
        return userRepo.deleteApplicationUserByUsernameEquals(username)+" deleted.";
    }

    @Override
    public String addMachine(RequestMachineAddDTO requestMachineAddDTO) {
        Machines machines = new Machines(
                requestMachineAddDTO.getMachineIP(),
                requestMachineAddDTO.getMachineName(),
                requestMachineAddDTO.getUsername(),
                requestMachineAddDTO.getPassword(),
                requestMachineAddDTO.getOSystem()
        );

        return machineRepo.save(machines).getMachineName()+" saved.";

    }

    @Override
    public List<Machines> getAllMachines() {
        return machineRepo.findAll();
    }

    @Override
    public String updateMAchine(MachineDTO machineDTO) {
        Optional<Machines> machines = machineRepo.findById(machineDTO.getMachineID());
        if (machines.isPresent()){
            Machines machines1 = machines.get();
            machines1.setMachineIP(machineDTO.getMachineIP());
            machines1.setMachineName(machineDTO.getMachineName());
            machines1.setUsername(machineDTO.getUsername());
            machines1.setUsername(machineDTO.getPassword());
            machines1.setOSystem(machineDTO.getOSystem());
            return machineRepo.save(machines1).getMachineID()+" updated.";
        }else {
            throw new MachineNotFoundExcepyion();
        }
    }
    @Override
    public String deleteMAchineByID(String machineID) {
        if(machineRepo.existsById(machineID)){
            machineRepo.deleteById(machineID);
            return machineID+" deleted.";
        }else {
            throw new MachineNotFoundExcepyion();
        }
    }
}

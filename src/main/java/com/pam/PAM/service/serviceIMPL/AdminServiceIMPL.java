package com.pam.PAM.service.serviceIMPL;

import com.pam.PAM.dto.ApplicationUserDTO;
import com.pam.PAM.dto.MachineDTO;
import com.pam.PAM.dto.request.RequestAddUserDTO;
import com.pam.PAM.dto.request.RequestMachineAddDTO;
import com.pam.PAM.exceptions.BadAttributeCredential;
import com.pam.PAM.exceptions.MachineNotFoundExcepyion;
import com.pam.PAM.exceptions.UserNotFoundException;
import com.pam.PAM.model.ApplicatioUserMongo;
import com.pam.PAM.model.Machinemongo;
import com.pam.PAM.repo.MachineRepoMongo;
import com.pam.PAM.repo.UserRepoMongo;
import com.pam.PAM.service.AdminService;
import com.pam.PAM.util.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AdminServiceIMPL implements AdminService {
//    @Autowired
//    private UserRepo userRepo;
    @Autowired
    private UserRepoMongo userRepoMongo;
    @Autowired
    private MachineRepoMongo machineRepoMongo;
//    @Autowired
//    private MachineRepo machineRepo;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BCryptPasswordEncoder cryptPasswordEncoder;

    @Override
    public String addUser(RequestAddUserDTO requestAddUserDTO) {


        ApplicatioUserMongo applicationUser = new ApplicatioUserMongo(
                UUID.randomUUID().toString().split("-")[0],
                requestAddUserDTO.getUsername(),
                cryptPasswordEncoder.encode(requestAddUserDTO.getPassword()),
                requestAddUserDTO.getEmail(),
                requestAddUserDTO.getRole(),
                cryptPasswordEncoder.encode(requestAddUserDTO.getNic())
        );
        userRepoMongo.insert(applicationUser);
        return applicationUser.getUsername() + " saved";


}

    public List<ApplicatioUserMongo> findAllUsers(){
        List<ApplicatioUserMongo> all = userRepoMongo.findAll();
        return all;

    }
    public List<ApplicatioUserMongo> findByUsername(String username){

        List<ApplicatioUserMongo> users = userRepoMongo.findByUserName(username);
        return users;
    }

    public String updateUser(ApplicationUserDTO requestAddUserDTO){
        Optional<ApplicatioUserMongo> byId = userRepoMongo.findById(requestAddUserDTO.getUserID());
        if (byId.isPresent()){
            ApplicatioUserMongo applicationUser  = byId.get();
            applicationUser.setUsername(requestAddUserDTO.getUsername());
            applicationUser.setEmail(requestAddUserDTO.getEmail());
            applicationUser.setPassword(cryptPasswordEncoder.encode(requestAddUserDTO.getPassword()));
            applicationUser.setRole(requestAddUserDTO.getRole());
            applicationUser.setNic(cryptPasswordEncoder.encode(requestAddUserDTO.getNic()));
            userRepoMongo.save(applicationUser);
            return applicationUser.getUsername()+" updated.";
        }else {
            throw new UserNotFoundException();
        }

    }
    public String deleteUser(String userID){
        Optional<ApplicatioUserMongo> byId = userRepoMongo.findById(userID);
        if(byId.get().getRole().equals("ADMIN")){
            throw new BadAttributeCredential();
        }else {
            userRepoMongo.deleteById(userID);
            return userID+" deleted.";
        }

    }

    @Override
    public String addMachine(RequestMachineAddDTO requestMachineAddDTO) {
        System.out.println(requestMachineAddDTO);
       Machinemongo machinemongo = new Machinemongo(
               UUID.randomUUID().toString().split("-")[0],
               requestMachineAddDTO.getMachineIP(),
               requestMachineAddDTO.getMachineName(),
               requestMachineAddDTO.getUsername(),
               requestMachineAddDTO.getPassword(),
               requestMachineAddDTO.getMachineOS()
       );
        machineRepoMongo.save(machinemongo);
        return machinemongo.getMachineName();
    }
    @Override
    public List<Machinemongo> getAllMachines() {
        return machineRepoMongo.findAll();
    }
    @Override
    public String updateMAchine(MachineDTO machineDTO) {
        Optional<Machinemongo> byId = machineRepoMongo.findById(machineDTO.getMachineID());
        if (byId.isPresent()){
            Machinemongo machines1 = byId.get();
            machines1.setMachineIP(machineDTO.getMachineIP());
            machines1.setMachineName(machineDTO.getMachineName());
            machines1.setUsername(machineDTO.getUsername());
            machines1.setUsername(machineDTO.getPassword());
            machines1.setMachineOS(machineDTO.getMachineOS());
            return machineRepoMongo.save(machines1).getMachineID()+" updated.";
        }else {
            throw new MachineNotFoundExcepyion();
        }
    }
    @Override
    public String deleteMAchineByID(String machineId) {
        Optional<Machinemongo> byId = machineRepoMongo.findById(machineId);
        if(byId.isPresent()){
            try {
                machineRepoMongo.deleteById(machineId);
            }catch (Exception e){
                System.out.println(e);
            }
            return machineId+" deleted.";
        }else {
            throw new MachineNotFoundExcepyion();
        }
    }
}

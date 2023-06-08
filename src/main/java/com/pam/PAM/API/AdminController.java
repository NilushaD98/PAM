package com.pam.PAM.API;

import com.pam.PAM.dto.ApplicationUserDTO;
import com.pam.PAM.dto.MachineDTO;
import com.pam.PAM.dto.request.RequestAddUserDTO;
import com.pam.PAM.dto.request.RequestMachineAddDTO;
import com.pam.PAM.model.ApplicationUser;
import com.pam.PAM.model.Machines;
import com.pam.PAM.service.AdminService;
import com.pam.PAM.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("addUser")
    public ResponseEntity<?> addUser(@RequestBody RequestAddUserDTO requestAddUserDTO){
        String saveStatus = adminService.addUser(requestAddUserDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(HttpStatus.CREATED.value(),"Saved Status : ", saveStatus ),HttpStatus.CREATED
        );
    }

    @GetMapping("getAll")
    public ResponseEntity<?> getAll(){
        List<ApplicationUser> allUsers = adminService.findAllUsers();
        return new ResponseEntity<>(
                allUsers, HttpStatus.OK
        );
    }

    @GetMapping(
            path="getByUsername",
            params = "username"
    )
    public ResponseEntity<?> getUserByName(@RequestParam(value ="username") String username){
        ApplicationUser byUsername = adminService.findByUsername(username);
        return new ResponseEntity<>(
                byUsername,HttpStatus.ACCEPTED
        );
    }

    @GetMapping(path="getByUserEmail",params = "email")
    public ResponseEntity<?> getUserByEmail(@RequestParam(value = "email") String email){
        return new ResponseEntity<>(
                adminService.findUserByUserEmail(email),HttpStatus.ACCEPTED
        );
    }

    @DeleteMapping(
            path="deleteByUsername",
            params = "username"
    )
    public ResponseEntity<?> deleteUserByName(@RequestParam(value ="username") String username){
        return new ResponseEntity<>(
                adminService.deleteUser(username),HttpStatus.ACCEPTED
        );
    }
    @PutMapping("userUpdate")
    public ResponseEntity<StandardResponse> userUpdate(@RequestBody ApplicationUserDTO applicationUserDTO){
        String updatedStatus = adminService.updateUser(applicationUserDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Update Status: ", updatedStatus),HttpStatus.OK
        );
    }

    @PostMapping("addMachine")
    public ResponseEntity<StandardResponse> addMachine(@RequestBody RequestMachineAddDTO requestMachineAddDTO){
        String savedStatus = adminService.addMachine(requestMachineAddDTO);
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.CREATED.value(), "Machine Saved Status; ", savedStatus),HttpStatus.CREATED
        );
    }

    @GetMapping("getAllMachines")
    public ResponseEntity<StandardResponse> getAllMachines(){
        List<Machines> machineDTOSList = adminService.getAllMachines();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"All Users :",machineDTOSList),HttpStatus.OK
        );
    }

    @PutMapping("updateMachineById")
    public ResponseEntity<StandardResponse> updateMachine(@RequestBody MachineDTO machineDTO){
        String updateStatus = adminService.updateMAchine(machineDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(HttpStatus.ACCEPTED.value(), "Upadted Status",updateStatus),HttpStatus.ACCEPTED
        );
    }

    @DeleteMapping(
            value = "deleteMachine",
            params = "machineID"
    )
    public ResponseEntity<StandardResponse> deleteMachineById(@RequestParam (value = "machineID") String machineID){
        String deleteStatus= adminService.deleteMAchineByID(machineID);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(HttpStatus.OK.value(),"MAchine Delete Status: ",deleteStatus),HttpStatus.OK
        );
    }
}
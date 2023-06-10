package com.pam.PAM.API;

import com.pam.PAM.dto.ApplicationUserDTO;
import com.pam.PAM.dto.MachineDTO;
import com.pam.PAM.dto.request.RequestAddUserDTO;
import com.pam.PAM.dto.request.RequestMachineAddDTO;
import com.pam.PAM.model.ApplicatioUserMongo;
import com.pam.PAM.model.Machinemongo;
import com.pam.PAM.service.AdminService;
import com.pam.PAM.util.StandardResponse;
import com.pam.PAM.util.mappers.MachineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/")
@CrossOrigin(origins = "*",methods = {RequestMethod.PUT,RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST})
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    MachineMapper machineMapper;

    @PostMapping("addUser")
    public ResponseEntity<?> addUser(@RequestBody RequestAddUserDTO requestAddUserDTO){
        String saveStatus = adminService.addUser(requestAddUserDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(HttpStatus.CREATED.value(),"Saved Status : ", saveStatus ),HttpStatus.CREATED
        );
    }

    @GetMapping("getAll")
    public ResponseEntity<?> getAll(){
        List<ApplicatioUserMongo> allUsers = adminService.findAllUsers();
        return new ResponseEntity<>(
                allUsers, HttpStatus.OK
        );
    }

    @GetMapping(
            path="getByUsername",
            params = "username"
    )
    public ResponseEntity<?> getUserByName(@RequestParam(value ="username") String username){
        List<ApplicatioUserMongo> byUsername = adminService.findByUsername(username);
        return new ResponseEntity<>(
                byUsername,HttpStatus.ACCEPTED
        );
    }


    @DeleteMapping(
            path="deleteByUserId",
            params = "userID"
    )
    public ResponseEntity<?> deleteBYuserID(@RequestParam(value ="userID") String userID){
        return new ResponseEntity<>(
                adminService.deleteUser(userID),HttpStatus.ACCEPTED
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
        List<Machinemongo> machineDTOSList = adminService.getAllMachines();
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
            params = "machineId"
    )
    public ResponseEntity<StandardResponse> deleteMachineById(@RequestParam (value = "machineId") String machineId){
        String deleteStatus= adminService.deleteMAchineByID(machineId);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(HttpStatus.OK.value(),"MAchine Delete Status: ",deleteStatus),HttpStatus.OK
        );
    }
}
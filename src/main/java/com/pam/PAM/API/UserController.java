package com.pam.PAM.API;

import com.pam.PAM.dto.MachineDTO;
import com.pam.PAM.dto.response.ResponseMachineNameDTO;
import com.pam.PAM.dto.response.ResponseUsernamesDTO;
import com.pam.PAM.service.UserService;
import com.pam.PAM.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.PUT,RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST})
@RequestMapping("/api/v1/users/")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("getAllUserNames")
    public ResponseEntity<StandardResponse> getAllUserNames(){
        System.out.println("user");
        List<ResponseUsernamesDTO> responseUsernamesDTOList = userService.findAllUsernames();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"User names from All users: ",responseUsernamesDTOList), HttpStatus.ACCEPTED
        );
    }
    @GetMapping("getAllMachinesname")
    public ResponseEntity<StandardResponse> getAllAMachinesName(){
        List<ResponseMachineNameDTO> responseMachineNameDTOList = userService.findMachineName();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"List of All Machines: ",responseMachineNameDTOList),HttpStatus.OK
        );
    }
    @GetMapping(
            value = {"getMachineDetailsByName"},
            params ={"machineName"}
    )
    public ResponseEntity<StandardResponse> getMachineDetailsByName(@RequestParam(value = "machineName")String machineName){
        MachineDTO machineDTO= userService.findMachineByName(machineName);
        return new ResponseEntity<>(
                new StandardResponse(200,machineName+" Machine's Details",machineDTO),HttpStatus.OK
        );
    }
}

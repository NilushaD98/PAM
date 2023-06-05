package com.pam.PAM.API;

import com.pam.PAM.dto.ApplicationUserDTO;
import com.pam.PAM.dto.request.RequestAddUserDTO;
import com.pam.PAM.model.ApplicationUser;
import com.pam.PAM.service.AdminService;
import com.pam.PAM.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/admin")
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
}

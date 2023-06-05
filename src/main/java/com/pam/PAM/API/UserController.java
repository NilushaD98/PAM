package com.pam.PAM.API;

import com.pam.PAM.dto.response.ResponseUsernamesDTO;
import com.pam.PAM.service.UserService;
import com.pam.PAM.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/users/")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("getAllUserNames")
    public ResponseEntity<StandardResponse> getAllUserNames(){
        List<ResponseUsernamesDTO> responseUsernamesDTOList = userService.findAllUsernames();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"User names from All users: ",responseUsernamesDTOList), HttpStatus.ACCEPTED
        );
    }
}

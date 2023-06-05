package com.pam.PAM.service.serviceIMPL;

import com.pam.PAM.dto.response.ResponseUsernamesDTO;
import com.pam.PAM.model.ApplicationUser;
import com.pam.PAM.repo.UserRepo;
import com.pam.PAM.service.UserService;
import com.pam.PAM.util.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceIMPL implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserMapper userMapper;

    public List<ResponseUsernamesDTO> findAllUsernames(){
        List<ApplicationUser> allUsernames = userRepo.findAllUsernames();
        return userMapper.EntityToDTO(allUsernames);

    }
}

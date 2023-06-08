package com.pam.PAM.service.serviceIMPL;

import com.pam.PAM.dto.response.ResponseMachineNameDTO;
import com.pam.PAM.dto.response.ResponseUsernamesDTO;
import com.pam.PAM.model.ApplicationUser;
import com.pam.PAM.model.Machines;
import com.pam.PAM.repo.MachineRepo;
import com.pam.PAM.repo.UserRepo;
import com.pam.PAM.service.UserService;
import com.pam.PAM.util.mappers.MachineMapper;
import com.pam.PAM.util.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserServiceIMPL implements UserService{
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MachineRepo machineRepo;
    @Autowired
    private MachineMapper machineMapper;
    public List<ResponseUsernamesDTO> findAllUsernames(){
//        List<ApplicationUser> allUsernames = userRepo.findAllUsernames();
//        return userMapper.EntityToDTO(allUsernames);
return null;
    }

    @Override
    public List<ResponseMachineNameDTO> findMachineName() {
//        List<Machines> machinesList = machineRepo.findAllMachines();
//        return machineMapper.EntityToDTO(machinesList);
        return null;
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        List<ApplicationUser> byUsername = userRepo.findByUsername(username);
//        ApplicationUser applicationUser = byUsername.get(0);
//        if(applicationUser==null){
//            throw new UsernameNotFoundException("User Not in database");
//        }else {
//            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
//            authorities.add(new SimpleGrantedAuthority(applicationUser.getRole()));
//            return new User(applicationUser.getUsername(),applicationUser.getPassword(),authorities);
//        }
//    }
}

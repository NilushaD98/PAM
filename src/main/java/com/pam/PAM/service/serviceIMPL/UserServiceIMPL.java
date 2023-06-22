package com.pam.PAM.service.serviceIMPL;

import com.pam.PAM.dto.response.ResponseMachineNameDTO;
import com.pam.PAM.dto.response.ResponseUsernamesDTO;
import com.pam.PAM.model.ApplicatioUserMongo;
import com.pam.PAM.model.Machinemongo;
import com.pam.PAM.repo.MachineRepoMongo;
import com.pam.PAM.repo.UserRepoMongo;
import com.pam.PAM.service.UserService;
import com.pam.PAM.util.mappers.MachineMapper;
import com.pam.PAM.util.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceIMPL implements UserService{

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MachineMapper machineMapper;
    @Autowired
    private UserRepoMongo userRepoMongo;
    @Autowired
    private MachineRepoMongo machineRepoMongo;
    public List<ResponseUsernamesDTO> findAllUsernames(){
        List<ApplicatioUserMongo> applicatioUserMongos = userRepoMongo.findAll();

        return userMapper.EntityToDTOMongo(applicatioUserMongos);
//        List<ApplicationUser> allUsernames = userRepo.findAllUsernames();
//        return userMapper.EntityToDTO(allUsernames);
    }
    @Override
    public List<ResponseMachineNameDTO> findMachineName() {
        List<Machinemongo> all = machineRepoMongo.findAll();
        return machineMapper.EntityToDTOMongoDB(all);
//        List<Machines> machinesList = machineRepo.findAllMachines();
//        return machineMapper.EntityToDTO(machinesList);
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

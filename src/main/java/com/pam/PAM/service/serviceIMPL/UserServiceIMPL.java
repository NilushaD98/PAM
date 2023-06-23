package com.pam.PAM.service.serviceIMPL;

import com.pam.PAM.dto.MachineDTO;
import com.pam.PAM.dto.response.ResponseMachineNameDTO;
import com.pam.PAM.dto.response.ResponseUsernamesDTO;
import com.pam.PAM.exceptions.MachineNotFoundExcepyion;
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
    }
    @Override
    public List<ResponseMachineNameDTO> findMachineName() {
        List<Machinemongo> all = machineRepoMongo.findAll();
        return machineMapper.EntityToDTOMongoDB(all);
    }

    @Override
    public MachineDTO findMachineByName(String machineName) {
        List<Machinemongo> byMachinename = machineRepoMongo.findByMachinename(machineName);
        if(byMachinename.isEmpty()){
            throw new MachineNotFoundExcepyion();
        }else {
            Machinemongo machinemongo = byMachinename.get(0);
            return machineMapper.DocumentToDTO(machinemongo);
        }
    }


}

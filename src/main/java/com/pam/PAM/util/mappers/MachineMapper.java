package com.pam.PAM.util.mappers;

import com.pam.PAM.dto.MachineDTO;
import com.pam.PAM.dto.response.ResponseMachineNameDTO;
import com.pam.PAM.model.ApplicatioUserMongo;
import com.pam.PAM.model.Machinemongo;
import com.pam.PAM.model.Machines;
import com.pam.PAM.repo.MachineRepoMongo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MachineMapper {
    List<ResponseMachineNameDTO> EntityToDTOMongoDB(List<Machinemongo> all);

    List<MachineDTO> EntityListToDTOList(List<ApplicatioUserMongo> allUsers);
}

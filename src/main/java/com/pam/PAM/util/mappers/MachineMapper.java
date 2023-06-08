package com.pam.PAM.util.mappers;

import com.pam.PAM.dto.response.ResponseMachineNameDTO;
import com.pam.PAM.model.Machines;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MachineMapper {
    List<ResponseMachineNameDTO> EntityToDTO(List<Machines> machinesList);
}

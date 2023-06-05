package com.pam.PAM.util.mappers;

import com.pam.PAM.dto.response.ResponseUsernamesDTO;
import com.pam.PAM.model.ApplicationUser;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    List<ResponseUsernamesDTO> EntityToDTO(List<ApplicationUser> allUsernames);
}

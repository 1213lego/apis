package com.lego.proapi.dto.mapper;

import com.lego.proapi.domain.User;
import com.lego.proapi.dto.model.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mappings({
            @Mapping(target = "password", source = "password", ignore = true)
    })
    UserDto userToUserDto(User user);
}

package com.lego.proapi.dto.mapper;

import com.lego.proapi.domain.Role;
import com.lego.proapi.domain.User;
import com.lego.proapi.domain.UserRole;
import com.lego.proapi.dto.model.RoleDto;
import com.lego.proapi.dto.model.UserDto;
import com.lego.proapi.util.Utils;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public UserDto userToUserDto(User user) {
        Map<String, Function<User, Object>> transforms = new HashMap<>();
        transforms.put("roles", (this::mapUserRolesToRolesDto));
        Set<String> ignoredFields = new HashSet<>();
        ignoredFields.add("password");
        return Utils.map(user, UserDto.class, transforms,ignoredFields);
    }

    @Override
    public User userDtoToUser(UserDto userDto) {
        Map<String, Function<UserDto, Object>> transforms = new HashMap<>();
        transforms.put("roles", (this::mapRolesToUserRoles));
        return Utils.map(userDto, User.class, transforms);
    }

    public List<UserRole> mapRolesToUserRoles(UserDto userDto) {
        if (userDto.getRoles() == null) return new ArrayList<>();
        return userDto.getRoles()
                .stream()
                .map((roleDto -> UserRole
                        .builder()
                        .role(
                                Role.builder()
                                        .rolName(roleDto.getRolName())
                                        .description(roleDto.getDescription())
                                        .id(roleDto.getId())
                                        .build()
                        )
                        .build()))
                .collect(Collectors.toList());
    }

    public List<RoleDto> mapUserRolesToRolesDto(User user) {
        if (user.getRoles() == null) return new ArrayList<>();
        return user.getRoles()
                .stream()
                .map(UserRole::getRole)
                .map((role) -> new RoleDto()
                        .setDescription(role.getDescription())
                        .setId(role.getId())
                        .setRolName(role.getRolName()))
                .collect(Collectors.toList());
    }
}

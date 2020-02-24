package com.lego.proapi.dto.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lego.proapi.domain.UserRole;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class UserDto {
    private String userName;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    @ToString.Exclude
    private List<UserRole> roles;
}

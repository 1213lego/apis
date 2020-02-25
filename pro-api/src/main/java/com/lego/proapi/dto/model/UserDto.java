package com.lego.proapi.dto.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lego.proapi.domain.Role;
import com.lego.proapi.domain.UserRole;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class UserDto {
    @NotBlank
    private String userName;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @ToString.Exclude
    private List<RoleDto> roles;
}

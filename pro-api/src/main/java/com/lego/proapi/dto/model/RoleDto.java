package com.lego.proapi.dto.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class RoleDto {
    private Short id;
    private String rolName;
    private String description;
}

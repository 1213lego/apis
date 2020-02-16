package com.lego.domain;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
public class UserRole extends BasicAuditable {
    @EmbeddedId
    private UserRoleKey id;
    @ManyToOne
    @MapsId("userId")
    private User user;
    @ManyToOne
    @MapsId("rolId")
    private Role role;
}

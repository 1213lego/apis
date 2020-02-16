package com.lego.domain;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class UserRoleKey implements Serializable {
    private Long userId;
    private Long rolId;
}

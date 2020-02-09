package com.lego.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table
public class State {
    public static State ACTIVE_STATE = State.builder().id(1).description("ACTIVE").build();
    public static State INACTIVE_STATE = State.builder().id(2).description("INACTIVE").build();
    @Id
    private Integer id;
    private String description;
}

package com.agurova.models;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Role {
    private Long roleId;
    private String name;
}

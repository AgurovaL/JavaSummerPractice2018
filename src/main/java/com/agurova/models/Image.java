package com.agurova.models;

import lombok.Data;

import javax.persistence.Entity;
import java.util.List;

@Entity
@Data
public class Image {
    private Long id;
    private String name;
    private String adress;
    private List<User> likedUsersList;
}

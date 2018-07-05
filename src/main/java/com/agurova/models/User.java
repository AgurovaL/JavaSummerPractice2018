package com.agurova.models;

import java.util.List;

import lombok.Data;
import javax.persistence.Entity;

@Entity
@Data
public class User {
    private Long id;
    private String name;
    private String login;
    private String password;
    private List<Image> favoriteImagesList;

}

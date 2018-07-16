package com.agurova.models;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;

@Entity
@Data
@ToString(exclude = {"images"})
public class User {
    private Long userId;
    private String name;
    private String login;
    private String password;
    private Set<Image> images = new HashSet<>();

    public void addImage(Image image) {
        images.add(image);
    }
}

package com.agurova.models;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;

@Entity
@Data
@ToString(exclude = {"favoriteImagesList"})
public class User {
    private Long id;
    private String name;
    private String login;
    private String password;
    private List<Image> favoriteImagesList;

    public static void addFavoriteImage(User currentUser, Image favoriteImage){
        if(currentUser.favoriteImagesList == null)
            currentUser.favoriteImagesList = new ArrayList<Image>();
        currentUser.favoriteImagesList.add(favoriteImage);
        favoriteImage.addLikedUser(currentUser);
    }
}

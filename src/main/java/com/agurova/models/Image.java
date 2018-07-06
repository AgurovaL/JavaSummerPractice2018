package com.agurova.models;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@ToString(exclude = {"likedUsersList"})
public class Image {
    private Long id;
    private String name;
    private String adress;
    private List<User> likedUsersList;

   // calling from User.addFavoriteImage
   protected void addLikedUser(User likedUser){
        if(likedUsersList == null)
            likedUsersList = new ArrayList<User>();
        likedUsersList.add(likedUser);
    }
}

//{
//        page: 1,
//        per_page: 15,
//        total_results: 236,
//        url: "https://www.pexels.com/search/example%20query/",
//        next_page: "https://api.pexels.com/v1/search/?page=2&per_page=15&query=example+query"
//        photos: [{
//        width: 1000,
//        height: 1000,
//        url: "https://www.pexels.com/photo/12345",
//        photographer: "Name",
//        src: {
//        original: "https://*.jpg",
//        large: "https://*.jpg",
//        large2x: "https://*.jpg",
//        medium: "https://*.jpg",
//        small: "https://*.jpg",
//        portrait: "https://*.jpg",
//        landscape: "https://*.jpg",
//        tiny: "https://*.jpg"
//        }, (NEXT PHOTOS)]
//        }
//        }
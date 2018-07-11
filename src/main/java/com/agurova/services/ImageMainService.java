package com.agurova.services;

import com.agurova.models.Image;

import java.util.List;

public interface ImageMainService {
    List<Image> getAllImages();

    void updateImages();

    List<Image> getImagesByTag(String tag);

    List<String> getFavoriteImages();

}

package com.agurova.services;

import com.agurova.models.Image;

import java.util.List;

public interface StockImagesService {
    List<Image> getAllImages();

    List<String> postAllImages(List<Image> images);

    Image getRandomImage();
}

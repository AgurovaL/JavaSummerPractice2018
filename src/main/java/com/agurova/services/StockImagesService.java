package com.agurova.services;

import com.agurova.models.Image;

public interface StockImagesService {
    void getImages();

    Image getRandomImage();
}

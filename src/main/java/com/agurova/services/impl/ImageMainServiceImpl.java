package com.agurova.services.impl;

import com.agurova.models.Image;
import com.agurova.services.ImageMainService;
import com.agurova.services.ImageRepositoryService;
import com.agurova.services.StockImagesService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ImageMainServiceImpl implements ImageMainService {
    private static final Logger LOG = Logger.getLogger(UnsplashStockImageServiceImpl.class);

    @Autowired
    private ImageRepositoryService imageRepositoryService;
    @Autowired
    private StockImagesService stockImagesService;

    public List<Image> getAllImages() {
        return imageRepositoryService.getAll();
    }

    //timed
    public void updateImages() {
        imageRepositoryService.deleteAll();
        List<Image> images = stockImagesService.getAllImages();
        for (Image image : images) {
            imageRepositoryService.save(image);
        }
    }

    public List<Image> getImagesByTag(String tag) {
        return stockImagesService.getImagesByTag(tag);
    }

    public List<String> getFavoriteImages() {
        List<String> resultStrings = new ArrayList<>();
        List<Image> images = imageRepositoryService.getAll();

        ObjectMapper mapper = new ObjectMapper();
        try {
            for (Image image : images) {
                //adding to result as Json string
                resultStrings.add(mapper.writeValueAsString(image));
            }
        } catch (JsonProcessingException e) {
            LOG.error("Converting to Json string error", e);
        }
        return resultStrings;
    }
}

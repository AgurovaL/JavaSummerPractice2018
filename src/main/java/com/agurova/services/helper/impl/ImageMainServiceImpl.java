package com.agurova.services.helper.impl;

import com.agurova.models.Image;
import com.agurova.services.dal.impl.ImageRepositoryServiceImpl;
import com.agurova.services.helper.ImageMainService;
import com.agurova.services.dal.ImageRepositoryService;
import com.agurova.services.external.StockImagesService;
import com.agurova.services.external.impl.UnsplashStockImageServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@EnableScheduling

public class ImageMainServiceImpl implements ImageMainService {
    private static final Logger LOG = Logger.getLogger(UnsplashStockImageServiceImpl.class);

    private ImageRepositoryService imageRepositoryService = new ImageRepositoryServiceImpl();
    private StockImagesService stockImagesService  = new UnsplashStockImageServiceImpl();

    public List<Image> getAllImages() {
        return imageRepositoryService.getAll();
    }

    //second, minute, hour, day of month, month, day(s) of week
    @Scheduled(cron = "0 0 0 * * ?")
    public void updateImages() {
        imageRepositoryService.deleteAll();
        List<Image> images = stockImagesService.getAllImages();
        for (Image image : images) {
            imageRepositoryService.save(image);
        }
        LOG.info("I'v done it!!!!");
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

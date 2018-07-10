package com.agurova.controllers;

import com.agurova.dal.ImageRepository;
import com.agurova.dal.impl.ImageRepositoryImpl;
import com.agurova.models.Image;
import com.agurova.services.StockImagesService;
import com.agurova.services.impl.UnsplashStockImagesService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ImageController {
    private static final Logger log = Logger.getLogger(ImageController.class);

    private static ImageRepository imageRepository = new ImageRepositoryImpl();
    private static StockImagesService stockImagesService = new UnsplashStockImagesService();

    @GetMapping("/allImages")
    public String imagesGet(ModelMap model) {
        List<Image> images = stockImagesService.getAllImages();
        for (Image image : images) {
            imageRepository.save(image);
        }

        model.addAttribute("message", images.size());
        log.info(images.size());
        return "allImages";
    }

    @PostMapping("/allImages")
    public void imagesPost() {
        List<Image> images = imageRepository.getAll();
        List<String> result = stockImagesService.postAllImages(images);
    }

    @GetMapping("/favoriteImages")
    public String favoriteImagesGet(ModelMap model) {
        model.addAttribute("message", "Hello!");
        return "favoriteImages";
    }

    @GetMapping("/categorizedImages")
    public String categorizedImagesGet(ModelMap model) {
        model.addAttribute("message", "Hello!");
        return "categorizedImages";
    }

}

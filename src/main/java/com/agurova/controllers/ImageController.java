package com.agurova.controllers;

import com.agurova.models.Image;
import com.agurova.models.User;
import com.agurova.services.image.external.StockImagesService;
import com.agurova.services.image.external.impl.UnsplashStockImageServiceImpl;
import com.agurova.services.image.helper.ImageMainService;
import com.agurova.services.user.impl.UserRepositoryServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/images")
@Controller
public class ImageController {
    private static final Logger LOG = Logger.getLogger(ImageController.class);

    private ImageMainService service;

    @GetMapping("/extra")
    public String extra(ModelMap model) {
        StockImagesService stockImageService = new UnsplashStockImageServiceImpl();
        Image image = stockImageService.getRandomImage();
        model.addAttribute("address", image.getAddress());
        return "extra";
    }

    @GetMapping("/all")
    public String imagesGet(ModelMap model) {
        service.updateImages();
        model.addAttribute("images", service.getAllImages());
        return "allImages";
    }

    @GetMapping("/favorite")
    public String favoriteImagesGet(ModelMap model) {

        //favorite image adding example
        User user = new User();
        user.setName("Ivan");
        user.setLogin("ivanov");
        user.setPassword("1234");
        Image image = new UnsplashStockImageServiceImpl().getRandomImage();
        user.addImage(image);
        new UserRepositoryServiceImpl().save(user);

        model.addAttribute("images", service.getFavoriteImages(user));
        return "favoriteImages";
    }

    @GetMapping("/categories")
    public String categorizedImagesGet(ModelMap model, String tag) {
        model.addAttribute("images", service.getImagesByTag(tag));
        return "categorizedImages";
    }
}

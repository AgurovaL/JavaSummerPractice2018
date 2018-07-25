package com.agurova.controllers;

import com.agurova.models.Image;
import com.agurova.models.User;
import com.agurova.services.image.external.StockImagesService;
import com.agurova.services.image.external.impl.UnsplashStockImageServiceImpl;
import com.agurova.services.image.helper.impl.ImageMainServiceImpl;
import com.agurova.services.user.dal.impl.UserRepositoryServiceImpl;
import lombok.Data;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/images")
@Controller
@Data
public class ImageController {
    private static final Logger LOG = Logger.getLogger(ImageController.class);
    private ImageMainServiceImpl service;
    private UserRepositoryServiceImpl userRepositoryService;

    @GetMapping("/extra")
    public String extra(Model model) {
        StockImagesService stockImageService = new UnsplashStockImageServiceImpl();
        Image image = stockImageService.getRandomImage();
        model.addAttribute("address", image.getAddress());
        return "extra";
    }

    @GetMapping("/all")
    public String imagesGet(Model model) {
        service.updateImages();
        model.addAttribute("images", service.getAllImages());
        return "allImages";
    }

    @GetMapping("/favorite")
    public String favoriteImagesGet(Model model) {

        //favorite image adding example
        User user = new User();
        user.setName("Ivan");
        user.setLogin("ivanov");
        user.setPassword("1234");
        Image image1 = new UnsplashStockImageServiceImpl().getRandomImage();
        Image image2 = new UnsplashStockImageServiceImpl().getRandomImage();
        user.addImage(image1);
        user.addImage(image2);
        userRepositoryService.save(user);

        model.addAttribute("images", service.getFavoriteImages(user));
        return "favoriteImages";
    }


}

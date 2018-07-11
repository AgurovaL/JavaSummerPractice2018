package com.agurova.controllers;

import com.agurova.models.Image;
import com.agurova.services.external.StockImagesService;
import com.agurova.services.external.impl.UnsplashStockImageServiceImpl;
import com.agurova.services.helper.ImageMainService;
import com.agurova.services.helper.impl.ImageMainServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@RequestMapping("/images")
@Controller
public class ImageController {
    private static final Logger LOG = Logger.getLogger(ImageController.class);

    private ImageMainService service = new ImageMainServiceImpl();

    @GetMapping("/extra")
    public String extra(ModelMap model){
        StockImagesService sis = new UnsplashStockImageServiceImpl();
        Image image = sis.getRandomImage();
        model.addAttribute(image.getAddress());
        return "extra";
    }

    @GetMapping("/all")
    public String imagesGet(ModelMap model) {
        List<Image> images = service.getAllImages();

        model.addAttribute("message", images.size());
        LOG.info(images.size());
        return "allImages";
    }

    @GetMapping("/favorite")
    public String favoriteImagesGet(ModelMap model) {
        service.updateImages();
       // List<String> images = service.getFavoriteImages();
        return "favoriteImages";
    }

    @GetMapping("/categories")
    public String categorizedImagesGet(ModelMap model, String tag) {
        service.getImagesByTag(tag);
        return "categorizedImages";
    }

}

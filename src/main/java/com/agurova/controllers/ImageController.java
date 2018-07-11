package com.agurova.controllers;

import com.agurova.models.Image;
import com.agurova.services.ImageMainService;
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

    //бины
    @Autowired
    private ImageMainService service; //= new ImageMainServiceImpl();

    @GetMapping("/all")
    public String imagesGet(ModelMap model) {
        List<Image> images = service.getAllImages();

        model.addAttribute("message", images.size());
        LOG.info(images.size());
        return "allImages";
    }

    @GetMapping("/favorite")
    public String favoriteImagesGet(ModelMap model) {
        List<String> images = service.getFavoriteImages();
        return "favoriteImages";
    }

    @GetMapping("/categories")
    public String categorizedImagesGet(ModelMap model, String tag) {
        service.getImagesByTag(tag);
        return "categorizedImages";
    }

}

package com.agurova.services.image.helper.impl;

import com.agurova.models.Image;
import com.agurova.models.User;
import com.agurova.services.image.dal.impl.ImageRepositoryServiceImpl;
import com.agurova.services.image.helper.ImageMainService;
import com.agurova.services.image.external.impl.UnsplashStockImageServiceImpl;
import lombok.Data;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
import java.util.Set;

@EnableScheduling
@Data
public class ImageMainServiceImpl implements ImageMainService {
    private static final Logger LOG = Logger.getLogger(ImageMainServiceImpl.class);

    private ImageRepositoryServiceImpl imageRepositoryService;
    private UnsplashStockImageServiceImpl stockImagesService;

    public List<Image> getAllImages() {
        return imageRepositoryService.getAll();
    }

    public List<Image> getImagesByTag(String tag) {
        return stockImagesService.getImagesByTag(tag);
    }

    public Set<Image> getFavoriteImages(User user) {
        return user.getImages();
    }

    //second, minute, hour, day of month, month, day(s) of week
    @Scheduled(cron = "0 0 0 * * ?")
    public void updateImages() {
        imageRepositoryService.deleteAll();
        List<Image> images = stockImagesService.getAllImages();
        for (Image image : images) {
            imageRepositoryService.save(image);
        }
        LOG.info("Images list is updated");
    }
}

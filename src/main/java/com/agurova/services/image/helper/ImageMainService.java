package com.agurova.services.image.helper;

import com.agurova.models.Image;
import com.agurova.models.User;
import java.util.List;
import java.util.Set;

public interface ImageMainService {
    List<Image> getAllImages();

    void updateImages();

    List<Image> getImagesByTag(String tag);

    Set<Image> getFavoriteImages(User user);
}

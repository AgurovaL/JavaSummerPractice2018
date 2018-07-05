package com.agurova.dal;

import com.agurova.models.Image;

import java.util.List;

public interface ImageRepository {
    List<Image> getAll();

    Image findByID(Long id);

    Image findByName(String name);

    void deleteImage(Long id);

    void deleteImage(String name);

    Long addImage(Image image);
}

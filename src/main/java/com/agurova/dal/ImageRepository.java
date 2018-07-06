package com.agurova.dal;

import com.agurova.models.Image;

import java.util.List;

public interface ImageRepository {
    List<Image> getAll();

    Image findByID(Long id);

    void deleteImage(Long id);

    void addImage(Image image);
}

package com.agurova.services;

import com.agurova.models.Image;

import java.util.List;

public interface ImageRepositoryService {
    List<Image> getAll();

    Image findByID(Long id);

    void delete(Long id);

    void deleteAll();

    void save(Image image);
}

package com.agurova.services.image.dal.impl;

import com.agurova.dal.ImageRepository;
import com.agurova.models.Image;
import com.agurova.services.image.dal.ImageRepositoryService;

import java.util.List;

public class ImageRepositoryServiceImpl implements ImageRepositoryService {

    private ImageRepository imageRepository;

    public List<Image> getAll() {
        return imageRepository.getAll();
    }

    public Image findByID(Long id) {
        return imageRepository.findByID(id);
    }

    public void delete(Long id) {
        imageRepository.delete(id);
    }

    public void deleteAll() {
        imageRepository.deleteAll();
    }

    public void save(Image image) {
        imageRepository.save(image);
    }
}

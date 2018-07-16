package com.agurova.services.image.dal.impl;

import com.agurova.dal.impl.ImageRepositoryImpl;
import com.agurova.models.Image;
import com.agurova.services.image.dal.ImageRepositoryService;
import lombok.Data;

import java.util.List;

@Data
public class ImageRepositoryServiceImpl implements ImageRepositoryService {

    private ImageRepositoryImpl imageRepository;

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

package com.agurova.services.impl;

import com.agurova.dal.ImageRepository;
import com.agurova.dal.impl.ImageRepositoryImpl;
import com.agurova.models.Image;
import com.agurova.services.ImageRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ImageRepositoryServiceImpl implements ImageRepositoryService {
    @Autowired
    private ImageRepository imageRepository;

    public List<Image> getAll(){
        return imageRepository.getAll();
    }

    public Image findByID(Long id){
        return imageRepository.findByID(id);
    }

    public void delete(Long id){
        imageRepository.delete(id);
    }

    public void deleteAll(){imageRepository.deleteAll();}

    public void save(Image image){
        imageRepository.save(image);
    }
}

package com.projet.projet.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.projet.beans.Photo;
import com.projet.projet.repositories.PhotoRepository;

@Service
public class PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    public List<Photo> getPhotosByRestaurant(Long id) {
        return photoRepository.findAllByRestaurantId(id);
    }

}
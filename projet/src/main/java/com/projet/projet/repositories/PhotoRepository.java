package com.projet.projet.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.projet.beans.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Integer>{

    List<Photo> findAllByRestaurantId(Long id);

}

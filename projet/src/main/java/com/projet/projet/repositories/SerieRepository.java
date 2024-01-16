package com.projet.projet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.projet.beans.Serie;

public interface SerieRepository extends JpaRepository<Serie, Long>{

    Serie getById(Long id);

}

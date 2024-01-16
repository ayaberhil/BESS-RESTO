package com.projet.projet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.projet.beans.Ville;

public interface VilleRepository extends JpaRepository<Ville, Long>{

    Ville getById(Long id);

}

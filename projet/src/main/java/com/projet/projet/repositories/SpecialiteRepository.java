package com.projet.projet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.projet.beans.Specialite;

public interface SpecialiteRepository extends JpaRepository<Specialite, Long>{

    Specialite getById(Long id);
    public Specialite findByNom(String nom);

}

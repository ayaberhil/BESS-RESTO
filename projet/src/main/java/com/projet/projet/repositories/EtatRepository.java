package com.projet.projet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.projet.beans.Etat;

public interface EtatRepository extends JpaRepository<Etat, Integer>{

    Etat findByNom(String nom);

}

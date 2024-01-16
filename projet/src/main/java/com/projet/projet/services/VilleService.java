package com.projet.projet.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.projet.beans.Ville;
import com.projet.projet.repositories.VilleRepository;

@Service
public class VilleService {

    @Autowired
    private VilleRepository villeRepository;

    public Ville save(String nom) {
        Ville ville = new Ville(nom);
        return villeRepository.save(ville);
    }

    public Ville edit(Long id, String nom) {
        Ville ville = villeRepository.getById(id);
        ville.setNom(nom);
        return villeRepository.save(ville);
    }

    public void delete(Long id) {
        Ville ville = villeRepository.getById(id);
        villeRepository.delete(ville);
    }

    public List<Ville> getAll() {
        return villeRepository.findAll();
    }

    public long count() {
        return villeRepository.count();
    }

}

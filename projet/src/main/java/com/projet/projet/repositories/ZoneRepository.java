package com.projet.projet.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.projet.beans.Ville;
import com.projet.projet.beans.Zone;

public interface ZoneRepository extends JpaRepository<Zone, Long>{
    
    Zone getById(Long id);
    List<Zone> findAllByVille(Ville ville);

}

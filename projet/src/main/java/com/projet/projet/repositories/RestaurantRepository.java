package com.projet.projet.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projet.projet.beans.Restaurant;
import com.projet.projet.beans.Serie;
import com.projet.projet.beans.Specialite;
import com.projet.projet.beans.User;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{

    Restaurant getById(Long id);
    List<Restaurant> findAllByProprietaire(User proprietaire);
    List<Restaurant> findAllBySerie(Serie serie);
    List<Restaurant> findAllBySpeciality(Specialite spec);
     
	@Query("SELECT DISTINCT r FROM Restaurant r WHERE r.zone.ville.id = :ville_id")
    List<Restaurant> findAllByVille(@Param("ville_id") Long ville);

    @Query("SELECT DISTINCT r FROM Restaurant r WHERE r.zone.id = :zone_id")
    List<Restaurant> findAllByZone(@Param("zone_id") Long zone);

    @Query("SELECT DISTINCT r FROM Restaurant r WHERE r.zone.ville.id = :ville_id AND r.zone.id = :zone_id")
    List<Restaurant> findAllByVilleAndZone(@Param("ville_id") Long villeId, @Param("zone_id") Long zoneId);

    
}

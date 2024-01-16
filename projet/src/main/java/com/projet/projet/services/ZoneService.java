package com.projet.projet.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.projet.beans.Ville;
import com.projet.projet.beans.Zone;
import com.projet.projet.repositories.VilleRepository;
import com.projet.projet.repositories.ZoneRepository;

@Service
public class ZoneService {

	@Autowired
	private ZoneRepository zoneRepository;
	
	@Autowired
	private VilleRepository villeRepository;
	
	public Zone save(String nom, Long id) {
		Ville ville = villeRepository.getById(id);
		Zone zone = new Zone(nom, ville);
		return zoneRepository.save(zone);
	}

	public Zone edit(Long id,String nom,Long idVille) {
		Zone zone = zoneRepository.getById(id);
		zone.setNom(nom);
		zone.setVille(villeRepository.getById(idVille));
		return zoneRepository.save(zone);
	}
	
	public void delete(Long id) {
		Zone zone = zoneRepository.getById(id);
		zoneRepository.delete(zone);
	}
    
    public List<Zone> getAll(){
		return zoneRepository.findAll();
	}
	
	public List<Zone> getAllZonesByVille(Long id) {
		Ville ville = villeRepository.getById(id);
		return zoneRepository.findAllByVille(ville);
	}
		
	public long count() {
		return zoneRepository.count();
	}
	
}

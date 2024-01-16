package com.projet.projet.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projet.projet.beans.Photo;
import com.projet.projet.beans.Restaurant;
import com.projet.projet.beans.Serie;
import com.projet.projet.beans.Specialite;
import com.projet.projet.beans.Ville;
import com.projet.projet.beans.Zone;
import com.projet.projet.repositories.VilleRepository;
import com.projet.projet.repositories.ZoneRepository;
import com.projet.projet.services.PhotoService;
import com.projet.projet.services.RestaurantService;
import com.projet.projet.services.SerieService;
import com.projet.projet.services.SpecialiteService;
import com.projet.projet.services.VilleService;
import com.projet.projet.services.ZoneService;

@RestController
@CrossOrigin(origins= "http://localhost:3000")
@RequestMapping("/projet/")
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;

	@Autowired
	private SpecialiteService specialiteService;
	
	@Autowired
	private PhotoService photoService;
	
	@Autowired 
	private ZoneService zoneService;

	@Autowired
	private VilleService villeService;

	@Autowired
	private SerieService serieService;

	@Autowired
	private VilleRepository villeRepository;

	@Autowired
	private ZoneRepository zoneRepository;
	
	@GetMapping(value="/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Restaurant>> getAllRestaus() {
		try {
			List<Restaurant> restaurants = new ArrayList<Restaurant>();
			restaurantService.getAll().forEach(restaurants::add);
			if (restaurants.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			System.out.println("--restos");
			return ResponseEntity.ok(restaurants); 
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/photos/{id}")
	public  ResponseEntity<List<Photo>>  show(@PathVariable("id")Long id) {
		List<Photo> images = photoService.getPhotosByRestaurant(id);
		return ResponseEntity.ok(images);
	}
	
	@GetMapping(value="/series", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Serie>> getAllSeries() {
		try {
			List<Serie> series = new ArrayList<Serie>();
			serieService.getAll().forEach(series::add);
			if (series.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				
			}
			System.out.println("--series");
			return ResponseEntity.ok(series); 
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/specialites", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Specialite>> getAllSpecs() {
		try {
			List<Specialite> specialites = new ArrayList<Specialite>();
			specialiteService.getAll().forEach(specialites::add);
			if (specialites.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				
			}
			System.out.println("--specialites");
			return ResponseEntity.ok(specialites); 
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/villes", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Ville>> getAllVilles() {
		try {
			List<Ville> villes = new ArrayList<Ville>();
			villeService.getAll().forEach(villes::add);
			if (villes.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				
			}
			System.out.println("--villes");
			return ResponseEntity.ok(villes); 
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/ville/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Restaurant>> getAllRestosByVille(@PathVariable("id") Long idVille) {
		try {
			List<Restaurant> restaurants = new ArrayList<Restaurant>();
			restaurantService.getAllByVille(villeRepository.getById(idVille)).forEach(restaurants::add);
			if (restaurants.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			System.out.println("---------------------------VILLE restaus");
			return ResponseEntity.ok(restaurants); 
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/zone/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Zone>> getAllZonesByVille(@PathVariable("id") Long idVille) {
		try {
			List<Zone> zones = zoneService.getAllZonesByVille(idVille);
			if (zones.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			System.out.println("--villes de zones");
			return ResponseEntity.ok(zones); 
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/serie/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Restaurant>> getAllRestosBySerie(@PathVariable("id") Long serie) {
		try {
			List<Restaurant> restos = restaurantService.getAllBySerie(serie);
			if (restos.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			System.out.println("--restos de serie");
			return ResponseEntity.ok(restos); 
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/specialite/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Restaurant>> getAllRestosBySpec(@PathVariable("id") Long serie) {
		try {
			List<Restaurant> restos = restaurantService.getAllBySpecialite(serie);
			if (restos.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			System.out.println("--restos de specialite");
			return ResponseEntity.ok(restos); 
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @GetMapping(value = "/{villeId}/{zoneId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Restaurant>> getAllByVilleAndZone(
            @PathVariable(name = "villeId") Long villeId,
            @PathVariable(name = "zoneId") Long zoneId) {

        try {
            Ville ville = villeRepository.getById(villeId);
            Zone zone = zoneRepository.getById(zoneId);

            List<Restaurant> restaurants = restaurantService.getAllByVilleAndZone(ville, zone);

            if (restaurants.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return ResponseEntity.ok(restaurants);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }





}

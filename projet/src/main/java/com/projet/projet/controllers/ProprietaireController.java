package com.projet.projet.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.projet.projet.beans.RestaurantDTO;
import com.projet.projet.beans.User;
import com.projet.projet.beans.Zone;
import com.projet.projet.services.RestaurantService;
import com.projet.projet.services.SerieService;
import com.projet.projet.services.SpecialiteService;
import com.projet.projet.services.UserServiceImpl;
import com.projet.projet.services.VilleService;
import com.projet.projet.services.ZoneService;

@Controller
@RequestMapping("/proprietaire/")
public class ProprietaireController {

    @Autowired
    private UserServiceImpl userImpl;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private ZoneService zoneService;

    @Autowired
    private VilleService villeService;

    @Autowired
    private SpecialiteService specialiteService;

    @Autowired
    private SerieService serieService;

    @GetMapping(value = "/index")
    public String ownerView(Model model, Authentication auth,
            @RequestParam(value = "user", required = false, defaultValue = "") String mail) {

        User user;
        if (!mail.equals("")) {
            user = userImpl.getByMail(mail);
        } else {
            String Mail = auth.getName();
            user = userImpl.getByMail(Mail);
        }

        model.addAttribute("restaurants", restaurantService.getAllByUser(user));
        model.addAttribute("user", user);

        return "proprietaire/index";
    }

	@GetMapping(value="/addRestaurant")
	public String addRestaurantView( Model model ) {
		model.addAttribute("restaurantDto",new RestaurantDTO());
		model.addAttribute("zones", zoneService.getAll());
		model.addAttribute("specialities", specialiteService.getAll());
		model.addAttribute("villes", villeService.getAll());
		model.addAttribute("series", serieService.getAll());
		
        
		return "proprietaire/addRestaurant";
	}

    @GetMapping(value = "/zones/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Zone>> getAllZonesByVille(@PathVariable("id") Long idVille) {
        try {
            List<Zone> zones = zoneService.getAllZonesByVille(idVille);
            if (zones.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            System.out.println("--VILLE zones");
            return ResponseEntity.ok(zones);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/addRestaurant", method = RequestMethod.POST)
    public String addRestaurant(@RequestParam("photos") MultipartFile[] photos,
            @RequestParam("nom") String nom,
            @RequestParam("adresse") String adresse,
            @RequestParam("zone") Long zone,
            @RequestParam("serie") Long serie,
            @RequestParam("lati") double lati,
            @RequestParam("longi") double longi,
            @RequestParam("hourop") String ouvrerture,
            @RequestParam("hourcl") String fermeture,
            @RequestParam("speciality") Long speciality,
            @RequestParam(value = "user", required = false, defaultValue = "") String mail,
            Model model, Authentication auth) {

        User user;
        if (!mail.equals("")) {
            user = userImpl.getByMail(mail);
        } else {
            String Mail = auth.getName();
            user = userImpl.getByMail(Mail);
        }

        restaurantService.addRestaurant(photos,nom,adresse,zone,serie,lati,longi,ouvrerture,fermeture,speciality,user);

        return "redirect:/proprietaire/addRestaurant";
    }

    @GetMapping("/deleteRestaurant/{id}")
    public String deleteRestaurant(@PathVariable("id") Long id) {
        restaurantService.delete(id);
        return "redirect:/proprietaire/addRestaurant";
    }
}

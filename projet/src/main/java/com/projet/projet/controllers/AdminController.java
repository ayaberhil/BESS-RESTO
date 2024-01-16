package com.projet.projet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projet.projet.beans.Restaurant;
import com.projet.projet.enums.EtatEnum;
import com.projet.projet.repositories.EtatRepository;
import com.projet.projet.repositories.RestaurantRepository;
import com.projet.projet.services.RestaurantService;
import com.projet.projet.services.SerieService;
import com.projet.projet.services.SpecialiteService;
import com.projet.projet.services.VilleService;
import com.projet.projet.services.ZoneService;

@Controller
@RequestMapping("/admin/")
public class AdminController {
	
	@Autowired
	private VilleService villeService;
	
	@Autowired
	private ZoneService zoneService;
	
	@Autowired
	private RestaurantService restaurantService;
	@Autowired
	private SerieService serieService;
	@Autowired
	private SpecialiteService specialiteService;
	@Autowired
	private RestaurantRepository restaurantRepository;
	@Autowired
	private EtatRepository etatRepository;
	
	@GetMapping(value="/restaurant")
    public String  restaurantView(Model model){
		model.addAttribute("restaurants", restaurantService.getAll());
        return "admin/restaurant";
    }

	@GetMapping(value="/login-auth")
    public String  restaurantjView(Model model){
		model.addAttribute("restaurants", restaurantService.getAll());
        return "login-auth";
    }
	
	@GetMapping(value="/ville")
    public String  villeView(Model model){
		model.addAttribute("villes", villeService.getAll());
        return "admin/ville";
    }
	
	@GetMapping(value="/zone")
    public String  zoneView(Model model){
		model.addAttribute("villes", villeService.getAll());
		model.addAttribute("zones", zoneService.getAll());
        return "admin/zone";
    }
	
	@GetMapping(value="/serie")
    public String  serieView(Model model){
		model.addAttribute("series", serieService.getAll());
        return "admin/serie";
    }
	
	@GetMapping(value="/specialite")
    public String  specialiteView(Model model){
		model.addAttribute("specialites", specialiteService.getAll());
        return "admin/specialite";
    }
	
	@GetMapping(value="/validateRestaurant/{id}")
    public String validate(@PathVariable(name="id")Long id){
		Restaurant r = restaurantRepository.getById(id);
		System.out.println(r.getNom());
		r.setEtat(etatRepository.findByNom(EtatEnum.VALIDE.name()));
		restaurantRepository.save(r);
        return "redirect:/admin/restaurant";
    }
	
	@GetMapping(value="/unvalidateRestaurant/{id}")
    public String unvalidate(@PathVariable(name="id")Long id){
		Restaurant r = restaurantRepository.getById(id);
		System.out.println(r.getNom());
		r.setEtat(etatRepository.findByNom(EtatEnum.NON_VALIDE.name()));
		restaurantRepository.save(r);
		return "redirect:/admin/restaurant";
    }
	
	
	@GetMapping(value="/deleteRestaurant/{id}")
    public String delete(@PathVariable(name="id")Long id){
		restaurantService.delete(id);
		return "redirect:/admin/restaurant";
    }
	
	
}

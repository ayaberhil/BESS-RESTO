package com.projet.projet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.projet.projet.services.RestaurantService;

@Controller
@RequestMapping("/proprietaire/")
public class PhotoController {
    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/addImages/{id}")
    public String save(@RequestParam("photos") MultipartFile[] photos, @PathVariable(name = "id") Long id) {
        restaurantService.addImages(photos, id);
        return "redirect:/proprietaire/index";
    }

}

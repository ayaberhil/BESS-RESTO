package com.projet.projet.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.projet.projet.beans.Specialite;
import com.projet.projet.services.SpecialiteService;

@Controller
@RequestMapping("/admin/")
public class SpecialiteController {
    @Autowired
    private SpecialiteService specialiteService;

    @PostMapping("/saveSpecialite")
    public String save(@RequestParam("nom") String nom, Model model) {
        specialiteService.save(nom);
        return "redirect:/admin/specialite";
    }

    @PostMapping("/editSpecialite/{id}")
    public String edit(@PathVariable(name = "id") Long id,
            @RequestParam("nom") String nom, Model model) {
        specialiteService.edit(id, nom);
        return "redirect:/admin/specialite";
    }

    @GetMapping("/deleteSpecialite/{id}")
    public String delete(@PathVariable(name = "id") Long id) {
        specialiteService.delete(id);
        return "redirect:/admin/specialite";
    }

    @GetMapping("/speciality")
    public String listSpecialites(Model model) {
        List<Specialite> specialites = specialiteService.getAll();

        System.out.println("Specialites: " + specialites);

        model.addAttribute("specialites", specialites);
        return "redirect:/admin/specialite";
    }

}

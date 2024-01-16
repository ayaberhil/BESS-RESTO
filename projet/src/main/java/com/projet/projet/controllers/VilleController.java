package com.projet.projet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.projet.projet.services.VilleService;

@Controller
@RequestMapping("/admin/")
public class VilleController {
    @Autowired
    private VilleService villeService;

    @PostMapping("/saveVille")
    public String save(@RequestParam("nom") String nom, Model model) {
        model.addAttribute("addSucc", "Ville ajoutée avec succés");
        model.addAttribute("saveFailed", "Ajout échoué");
        villeService.save(nom);
        return "redirect:/admin/ville";
    }

    @PostMapping("/editVille/{id}")
    public String edit(@PathVariable(name = "id") Long id,
            @RequestParam("nom") String nom, Model model) {
        villeService.edit(id, nom);
        return "redirect:/admin/ville";
    }

    @GetMapping("/deleteVille/{id}")
    public String delete(@PathVariable(name = "id") Long id) {
        villeService.delete(id);
        return "redirect:/admin/ville";
    }

}

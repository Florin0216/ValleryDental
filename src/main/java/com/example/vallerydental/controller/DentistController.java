package com.example.vallerydental.controller;


import com.example.vallerydental.model.Dentist;
import com.example.vallerydental.service.impl.DentistServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class DentistController {
    private final DentistServiceImpl dentistService;

    @Autowired
    public DentistController(DentistServiceImpl dentistService) {
        this.dentistService = dentistService;
    }

    @GetMapping("/dentists/create")
    public String createDentistForm(Model model) {
        model.addAttribute("dentist", new Dentist());
        return "Dentist/createDentist";
    }

    @PostMapping("/dentists")
    public String createDentist(@ModelAttribute("dentist") Dentist dentist) {
        dentistService.createDentist(dentist);
        return "redirect:/admin/dentists";
    }

    @GetMapping("/dentists/update/{id}")
    public String updateDentistForm(@PathVariable Integer id, Model model) {
        Dentist dentist = dentistService.getDentistById(id);
        model.addAttribute("dentist", dentist);
        return "Dentist/updateDentist";
    }

    @PostMapping("/dentists/update/{id}")
    public String updateDentist(@ModelAttribute("dentist") Dentist dentist, @PathVariable Integer id) {
        dentistService.updateDentist(id, dentist);
        return "redirect:/admin/dentists";
    }

    @GetMapping("/dentists/delete/{id}")
    public String deleteDentist(@PathVariable Integer id) {
        dentistService.deleteDentist(id);
        return "redirect:/admin/dentists";
    }
}

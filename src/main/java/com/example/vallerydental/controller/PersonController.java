package com.example.vallerydental.controller;

import com.example.vallerydental.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.vallerydental.service.impl.PersonServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class PersonController {
    private final PersonServiceImpl personServiceImpl;

    public PersonController(PersonServiceImpl personServiceImpl) {
        this.personServiceImpl = personServiceImpl;
    }

    @GetMapping("/patients/create")
    public String createPatientForm(Model model) {
        model.addAttribute("patient", new Person());
        return "Patient/createPatient";
    }

    @PostMapping("/patients")
    public String createPatient(@ModelAttribute("patient") Person person) {
        personServiceImpl.addPerson(person);
        return "redirect:/admin/patients";
    }

    @GetMapping("/patients/update/{id}")
    public String updatePatientForm(@PathVariable Integer id, Model model) {
        Person patient = personServiceImpl.getPatientById(id);
        model.addAttribute("patient", patient);
        return "Patient/updatePatient";
    }

    @PostMapping("/patients/update/{id}")
    public String updatePatient(@ModelAttribute("patient") Person person, @PathVariable Integer id) {
        personServiceImpl.updatePatient(id, person);
        return "redirect:/admin/patients";
    }

    @GetMapping("/patients/delete/{id}")
    public String deletePatient(@PathVariable Integer id) {
        personServiceImpl.deletePatient(id);
        return "redirect:/admin/patients";
    }
}
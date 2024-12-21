package com.example.vallerydental.controller;

import com.example.vallerydental.model.Patient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.vallerydental.service.PatientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/patients")
    public String getAllPatients(Model model) {
        List<Patient> patients = patientService.getAllPatients();
        model.addAttribute("patients", patients);
        return "Patient/patients";
    }

    @GetMapping("patients/create")
    public String createPatientForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "Patient/createPatient";
    }

    @PostMapping("/patients")
    public String createPatient(@ModelAttribute("patient") Patient patient) {
        patientService.addPatient(patient);
        return "redirect:/patients";
    }

    @GetMapping("patients/update/{id}")
    public String updatePatientForm(@PathVariable Integer id, Model model) {
        Patient patient = patientService.getPatientById(id);
        model.addAttribute("patient", patient);
        return "Patient/updatePatient";
    }

    @PostMapping("patients/update/{id}")
    public String updatePatient(@ModelAttribute("patient") Patient patient, @PathVariable Integer id) {
        patientService.updatePatient(id, patient);
        return "redirect:/patients";
    }

    @GetMapping("/patients/delete/{id}")
    public String deletePatient(@PathVariable Integer id) {
        patientService.deletePatient(id);
        return "redirect:/patients";
    }
}
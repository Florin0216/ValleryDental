package com.example.vallerydental.controller;

import com.example.vallerydental.model.Patient;
import com.example.vallerydental.service.PatientService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContentController {
    private final PatientService patientService;

    public ContentController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/home")
    public String DisplayHomepage() {
        return "Home";
    }

    @GetMapping("/register")
    public String DisplayRegisterPage(Model model) {
        model.addAttribute("patient", new Patient());
        return "Register";
    }

    @PostMapping("/register")
    public String registerPatient(@ModelAttribute("patient") Patient patient) {
        patientService.addPatient(patient);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String DisplayLoginPage(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return "Login";
        } else {
            return "redirect:/user/appointments/9";
        }
    }
}

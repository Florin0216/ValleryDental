package com.example.vallerydental.controller;

import com.example.vallerydental.model.Patient;
import com.example.vallerydental.model.Receipt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.vallerydental.service.PatientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

}

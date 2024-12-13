package com.example.vallerydental.controller;

import com.example.vallerydental.model.Dentist;
import com.example.vallerydental.service.DentistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DentistController {
    private final DentistService dentistService;

    public DentistController(DentistService dentistService) {
        this.dentistService = dentistService;
    }

    @GetMapping("/display")
    public ResponseEntity<List<Dentist>> displayDoctors() {
        List<Dentist> doctors = dentistService.getAllDoctors();
        return ResponseEntity.ok().body(doctors);
    }
}

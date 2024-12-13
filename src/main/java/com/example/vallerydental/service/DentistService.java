package com.example.vallerydental.service;

import com.example.vallerydental.model.Dentist;
import com.example.vallerydental.repository.DentistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DentistService {
    private final DentistRepository dentistRepository;

    public DentistService(DentistRepository dentistRepository) {
        this.dentistRepository = dentistRepository;
    }

    public List<Dentist> getAllDoctors() {
        return dentistRepository.findAll();
    }
}

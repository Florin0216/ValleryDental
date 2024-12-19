package com.example.vallerydental.service;

import com.example.vallerydental.model.Patient;
import com.example.vallerydental.repository.PatientRepository;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient getPatientById(Integer id) {
        return patientRepository.findById(id).orElse(null);
    }
}

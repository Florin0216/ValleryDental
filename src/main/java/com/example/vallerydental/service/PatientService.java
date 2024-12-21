package com.example.vallerydental.service;

import com.example.vallerydental.model.Patient;
import com.example.vallerydental.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient getPatientById(Integer id) {
        return patientRepository.findById(id).orElse(null);
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public void addPatient(Patient patient) {
        patientRepository.save(patient);
    }

    public void updatePatient(Integer id,Patient patient) {
        Patient patientToUpdate = patientRepository.getReferenceById(id);
        patientToUpdate.setFirstname(patient.getFirstname());
        patientToUpdate.setLastname(patient.getLastname());
        patientToUpdate.setEmail(patient.getEmail());
        patientToUpdate.setPhone(patient.getPhone());
        patientToUpdate.setGender(patient.getGender());
        patientToUpdate.setCnppatient(patient.getCnppatient());
        patientToUpdate.setDateofbirth(patient.getDateofbirth());
        patientRepository.save(patientToUpdate);
    }

    public void deletePatient(Integer id) {
        patientRepository.deleteById(id);
    }
}

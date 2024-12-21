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

    public Dentist getDentistById(int id) {
        return dentistRepository.findById(id).orElse(null);
    }

    public List<Dentist> getAllDentist() {
        return dentistRepository.findAll();
    }

    public void createDentist(Dentist dentist) {
        dentistRepository.save(dentist);
    }

    public void updateDentist(Integer id, Dentist dentist) {
        Dentist existingDentist = dentistRepository.getReferenceById(id);
        existingDentist.setFirstname(dentist.getFirstname());
        existingDentist.setLastname(dentist.getLastname());
        existingDentist.setEmail(dentist.getEmail());
        existingDentist.setPhone(dentist.getPhone());
        existingDentist.setGender(dentist.getGender());
        existingDentist.setCnpdentist(dentist.getCnpdentist());
        existingDentist.setSpecialization(dentist.getSpecialization());
        dentistRepository.save(existingDentist);
    }

    public void deleteDentist(int id) {
        dentistRepository.deleteById(id);
    }
}

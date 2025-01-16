package com.example.vallerydental.service;

import com.example.vallerydental.model.Dentist;
import com.example.vallerydental.model.Role;
import com.example.vallerydental.repository.DentistRepository;
import com.example.vallerydental.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DentistService {
    private final DentistRepository dentistRepository;
    private final RoleRepository roleRepository;

    public DentistService(DentistRepository dentistRepository, RoleRepository roleRepository) {
        this.dentistRepository = dentistRepository;
        this.roleRepository = roleRepository;
    }

    public Dentist getDentistById(int id) {
        return dentistRepository.findById(id).orElse(null);
    }

    public List<Dentist> getAllDentist() {
        return dentistRepository.findAll();
    }

    public void createDentist(Dentist dentist) {
        Role userRole = roleRepository.findByName("USER");
        Role adminRole = roleRepository.findByName("ADMIN");
        dentist.getUser().setRoles(List.of(userRole, adminRole));
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

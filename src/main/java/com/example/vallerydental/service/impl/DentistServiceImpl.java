package com.example.vallerydental.service.impl;

import com.example.vallerydental.model.Dentist;
import com.example.vallerydental.model.Role;
import com.example.vallerydental.repository.DentistRepository;
import com.example.vallerydental.repository.RoleRepository;
import com.example.vallerydental.service.DentistService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DentistServiceImpl implements DentistService {
    private final DentistRepository dentistRepository;
    private final RoleRepository roleRepository;

    public DentistServiceImpl(DentistRepository dentistRepository, RoleRepository roleRepository) {
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
        Role userRole = roleRepository.findByName("ROLE_USER");
        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        dentist.getPerson().getUser().setRoles(List.of(userRole, adminRole));
        dentistRepository.save(dentist);
    }

    public void updateDentist(Integer id, Dentist dentist) {
        Dentist existingDentist = dentistRepository.getReferenceById(id);
        existingDentist.getPerson().setFirstName(dentist.getPerson().getFirstName());
        existingDentist.getPerson().setLastName(dentist.getPerson().getLastName());
        existingDentist.getPerson().setCnp(dentist.getPerson().getCnp());
        existingDentist.getPerson().setEmail(dentist.getPerson().getEmail());
        existingDentist.getPerson().setGender(dentist.getPerson().getGender());
        existingDentist.getPerson().setPhone(dentist.getPerson().getPhone());
        existingDentist.getPerson().setDateOfBirth(dentist.getPerson().getDateOfBirth());
        existingDentist.setSpecialization(dentist.getSpecialization());
        dentistRepository.save(existingDentist);
    }

    public void deleteDentist(int id) {
        dentistRepository.deleteById(id);
    }


}

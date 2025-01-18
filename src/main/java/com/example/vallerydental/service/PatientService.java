package com.example.vallerydental.service;

import com.example.vallerydental.model.Patient;
import com.example.vallerydental.model.Role;
import com.example.vallerydental.model.User;
import com.example.vallerydental.repository.PatientRepository;
import com.example.vallerydental.repository.RoleRepository;
import com.example.vallerydental.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    private final PatientRepository patientRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public PatientService(PatientRepository patientRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.patientRepository = patientRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Patient getPatientById(Integer id) {
        return patientRepository.findById(id).orElse(null);
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public void addPatient(Patient patient) {
        String encodedPassword = passwordEncoder.encode(patient.getUser().getPassword());
        patient.getUser().setPassword(encodedPassword);
        Role role = roleRepository.findByName("ROLE_USER");
        patient.getUser().setRoles(List.of(role));
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

    public Patient findByUser(User user) {
        return patientRepository.findByUser(user);
    }
}

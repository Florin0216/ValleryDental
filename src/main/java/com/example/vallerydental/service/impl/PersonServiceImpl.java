package com.example.vallerydental.service.impl;

import com.example.vallerydental.model.Person;
import com.example.vallerydental.model.Role;
import com.example.vallerydental.model.User;
import com.example.vallerydental.repository.PersonRepository;
import com.example.vallerydental.repository.RoleRepository;
import com.example.vallerydental.service.PersonService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public PersonServiceImpl(PersonRepository personRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Person getPatientById(Integer id) {
        return personRepository.findById(id).orElse(null);
    }

    public List<Person> getAllPatients() {
        return personRepository.findAll();
    }

    public void addPerson(Person person) {
        String encodedPassword = passwordEncoder.encode(person.getUser().getPassword());
        person.getUser().setPassword(encodedPassword);
        Role role = roleRepository.findByName("ROLE_USER");
        person.getUser().setRoles(List.of(role));
        personRepository.save(person);
    }

    public void updatePatient(Integer id,Person person) {
        Person patientToUpdate = personRepository.getReferenceById(id);
        patientToUpdate.setFirstName(person.getFirstName());
        patientToUpdate.setLastName(person.getLastName());
        patientToUpdate.setEmail(person.getEmail());
        patientToUpdate.setPhone(person.getPhone());
        patientToUpdate.setGender(person.getGender());
        patientToUpdate.setCnp(person.getCnp());
        patientToUpdate.setDateOfBirth(person.getDateOfBirth());
        personRepository.save(patientToUpdate);
    }

    public void deletePatient(Integer id) {
        personRepository.deleteById(id);
    }

    public Person findByUser(User user) {
        return personRepository.findByUser(user);
    }
}

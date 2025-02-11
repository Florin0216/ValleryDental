package com.example.vallerydental.service;

import com.example.vallerydental.model.Person;
import com.example.vallerydental.model.User;

import java.util.List;

public interface PersonService {

    Person getPatientById(Integer id);

    List<Person> getAllPatients();

    void addPerson(Person person);

    void updatePatient(Integer id,Person person);

    void deletePatient(Integer id);

    Person findByUser(User user);
}

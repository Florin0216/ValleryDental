package com.example.vallerydental.service;

import com.example.vallerydental.model.Dentist;

import java.util.List;

public interface DentistService {

    Dentist getDentistById(int id);

    List<Dentist> getAllDentist();

    void createDentist(Dentist dentist);

    void updateDentist(Integer id, Dentist dentist);

    void deleteDentist(int id);
}

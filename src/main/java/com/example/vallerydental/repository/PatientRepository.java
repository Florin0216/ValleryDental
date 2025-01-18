package com.example.vallerydental.repository;

import com.example.vallerydental.model.Patient;
import com.example.vallerydental.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    Patient findByUser(User user);
}

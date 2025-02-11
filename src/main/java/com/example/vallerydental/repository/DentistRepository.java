package com.example.vallerydental.repository;


import com.example.vallerydental.model.Dentist;
import com.example.vallerydental.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DentistRepository extends JpaRepository<Dentist, Integer> {

}

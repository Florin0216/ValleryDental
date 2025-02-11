package com.example.vallerydental.repository;

import com.example.vallerydental.model.Person;
import com.example.vallerydental.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    Person findByUser(User user);
}

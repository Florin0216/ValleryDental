package com.example.vallerydental.repository;

import com.example.vallerydental.model.Appointment;
import com.example.vallerydental.model.Dentist;
import com.example.vallerydental.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    List<Appointment> getAppointmentsByIdAndStatus(Person person,String status);

    Appointment findAppointmentById(Integer appointmentId);

    List<Appointment> findAppointmentByAppointmentDate(LocalDate appointmentDate);

    List<Appointment> findAppointmentsByStatus(String status);

    List<Appointment> findAppointmentsByAppointmentDateAndDentist(LocalDate appointmentDate, Dentist dentist);

    List<Appointment> findAppointmentsByAppointmentDate(LocalDate appointmentDate);

    Appointment getAppointmentByAppointmentConfirmation(String appointmentConfirmation);

    List<Appointment> findAppointmentsByPerson(Person person);

    List<Appointment> findAppointmentsByPersonAndStatus(Person person, String status);
}

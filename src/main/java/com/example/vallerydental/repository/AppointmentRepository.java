package com.example.vallerydental.repository;

import com.example.vallerydental.model.Appointment;
import com.example.vallerydental.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    List<Appointment> findByPatient_Id(Integer patientId);

    Appointment findAppointmentById(Integer appointmentId);

}

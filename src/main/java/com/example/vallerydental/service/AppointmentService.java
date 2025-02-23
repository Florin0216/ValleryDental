package com.example.vallerydental.service;

import com.example.vallerydental.model.Appointment;
import com.example.vallerydental.model.Dentist;
import com.example.vallerydental.model.Person;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentService {

    List<Appointment> getAppointmentsByIdAndStatus(Person person,String status);

    void addAppointment(Dentist selectedDentist, Person selectedPerson, LocalDate appointmentDate, Appointment appointment);

    Appointment getAppointmentById(Integer id);

    void updateAppointment(Integer id, Appointment updatedAppointment);

    void deleteAppointment(Integer id);

    List<Appointment> getCurrentAppointments();

    List<Appointment> getCompletedAppointments();

    List<String> getAvailableHours(LocalDate date, Dentist dentist);

    @Scheduled(cron = "0 * * * * *")
    void sendAppointmentsReminder();

    Appointment getAppointmentByToken(String token);

    void saveAppointment(Appointment appointment);
}

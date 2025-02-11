package com.example.vallerydental.service.impl;

import com.example.vallerydental.model.Appointment;
import com.example.vallerydental.model.Dentist;
import com.example.vallerydental.model.Person;
import com.example.vallerydental.repository.AppointmentRepository;
import com.example.vallerydental.service.AppointmentService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final EmailServiceImpl emailServiceImpl;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, EmailServiceImpl emailServiceImpl) {
        this.appointmentRepository = appointmentRepository;
        this.emailServiceImpl = emailServiceImpl;
    }

    public List<Appointment> getAppointmentsForPatient(Integer id) {
        return appointmentRepository.findAppointmentByPerson_PersonID(id);
    }

    public void addAppointment(Dentist selectedDentist, Person selectedPerson,LocalDate appointmentDate, Appointment appointment) {
        appointment.setPerson(selectedPerson);
        appointment.setDentist(selectedDentist);
        appointment.setAppointmentDate(appointmentDate);
        appointment.setStatus("Scheduled");
        emailServiceImpl.sendSimpleMessage(selectedPerson.getEmail(),"Test","Acesta este un email de test");
        appointmentRepository.save(appointment);
    }

    public Appointment getAppointmentById(Integer id) {
        return appointmentRepository.findAppointmentById(id);
    }

    public void updateAppointment(Integer id, Appointment updatedAppointment){
        Appointment existingAppointment = appointmentRepository.findAppointmentById(id);
        existingAppointment.setAppointmentTime(updatedAppointment.getAppointmentTime());
        existingAppointment.setAppointmentDate(updatedAppointment.getAppointmentDate());
        existingAppointment.setDentist(updatedAppointment.getDentist());
        existingAppointment.setStatus(updatedAppointment.getStatus());
        appointmentRepository.save(existingAppointment);
    }

    public void deleteAppointment(Integer id) {
        appointmentRepository.deleteById(id);
    }

    public List<Appointment> getCurrentAppointments() {
        LocalDate today = LocalDate.now();
        return appointmentRepository.findAppointmentByAppointmentDate(today);
    }

    public List<Appointment> getCompletedAppointments() {
        return appointmentRepository.findAppointmentsByStatus("Completed");
    }

    public List<String> getAvailableHours(LocalDate date, Dentist dentist) {
        List<String> allHours = List.of("09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00");

        List<Appointment> existingAppointments = appointmentRepository.findAppointmentsByAppointmentDateAndDentist(date, dentist);

        List<String> bookedHours = new ArrayList<>();
        for (Appointment appointment : existingAppointments) {
            bookedHours.add(appointment.getAppointmentTime());
        }

        List<String> availableHours = new ArrayList<>();
        for (String hour : allHours) {
            if (!bookedHours.contains(hour)) {
                availableHours.add(hour);
            }
        }

        return availableHours;
    }

    @Scheduled(cron = "0 * * * * *")
    public void sendAppointmentsReminder() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        List<Appointment> appointments = appointmentRepository.findAppointmentsByAppointmentDate(tomorrow);

        for (Appointment appointment : appointments) {
            if(!appointment.isAppointmentReminder()){
                String personEmail = appointment.getPerson().getEmail();
                emailServiceImpl.sendSimpleMessage(personEmail,"Test","Acesta este un reminder!");
                appointment.setAppointmentReminder(true);
                appointmentRepository.save(appointment);
            }
        }
    }

}

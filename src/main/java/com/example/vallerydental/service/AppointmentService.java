package com.example.vallerydental.service;

import com.example.vallerydental.model.Appointment;
import com.example.vallerydental.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public List<Appointment> getAppointmentsForPatient(Integer id) {
        return appointmentRepository.findByPatient_Id(id);
    }

    public void addAppointment(Appointment appointment) {
        appointmentRepository.save(appointment);
    }

    public Appointment getAppointmentById(Integer id) {
        return appointmentRepository.findAppointmentById(id);
    }

    public void updateAppointment(Integer id, Appointment updatedAppointment){
        Appointment existingAppointment = appointmentRepository.findAppointmentById(id);
        existingAppointment.setAppointmentdate(updatedAppointment.getAppointmentdate());
        existingAppointment.setDentist(updatedAppointment.getDentist());
        existingAppointment.setStatus(updatedAppointment.getStatus());
        appointmentRepository.save(existingAppointment);
    }

    public void deleteAppointment(Integer id) {
        appointmentRepository.deleteById(id);
    }
}

package com.example.vallerydental.controller;

import com.example.vallerydental.model.Appointment;
import com.example.vallerydental.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AppointmentConfirmationController {
    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentConfirmationController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/confirm-appointment")
    public String showConfirmAppointmentForm(@RequestParam String token) {
        Appointment appointment = appointmentService.getAppointmentByToken(token);

        if (appointment != null) {
            appointment.setStatus("Scheduled");
            appointment.setAppointmentConfirmation(null);
            appointmentService.saveAppointment(appointment);
        }

        return "Appointment/confirmAppointment";
    }
}

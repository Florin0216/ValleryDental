package com.example.vallerydental.controller;

import com.example.vallerydental.model.Appointment;
import com.example.vallerydental.model.Dentist;
import com.example.vallerydental.model.Patient;
import com.example.vallerydental.service.AppointmentService;
import com.example.vallerydental.service.DentistService;
import com.example.vallerydental.service.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class AppointmentController {
    private final AppointmentService appointmentService;
    private final DentistService dentistService;
    private final PatientService patientService;

    public AppointmentController(AppointmentService appointmentService, DentistService dentistService, PatientService patientService) {
        this.appointmentService = appointmentService;
        this.dentistService = dentistService;
        this.patientService = patientService;
    }

    @GetMapping("/appointments/{id}")
    public String getAppointmentsForPatient(@PathVariable Integer id, Model model) {
        List<Appointment> appointments = appointmentService.getAppointmentsForPatient(id);
        Patient patient = patientService.getPatientById(id);
        model.addAttribute("patient", patient);
        model.addAttribute("appointments", appointments);
        return "Appointment/patientAppointments";
    }

    @GetMapping("/appointments/new/{id}")
    public String createAppointment(@PathVariable Integer id, Model model) {
        Appointment appointment = new Appointment();
        Patient patient = patientService.getPatientById(id);
        List<Dentist> dentists = dentistService.getAllDentist();
        appointment.setPatient(patient);
        model.addAttribute("dentists", dentists);
        model.addAttribute("patient", patient);
        model.addAttribute("appointment", appointment);
        return "Appointment/newAppointment";
    }

    @PostMapping("/appointments")
    public String saveAppointment(@ModelAttribute("appointment") Appointment appointment) {
        appointment.setStatus("Scheduled");
        appointmentService.addAppointment(appointment);
        return "redirect:/appointments/" + appointment.getPatient().getId();
    }

    @GetMapping("/appointments/edit/{id}")
    public String editAppointment(@PathVariable Integer id, Model model) {
        Appointment appointment = appointmentService.getAppointmentById(id);
        List<Dentist> dentists = dentistService.getAllDentist();
        model.addAttribute("dentists", dentists);
        model.addAttribute("appointment", appointment);
        return "Appointment/editAppointment";
    }

    @PostMapping("/appointments/edit/{id}")
    public String updateAppointment(@PathVariable Integer id, @ModelAttribute("appointment") Appointment appointment) {
        appointmentService.updateAppointment(id, appointment);
        return "redirect:/appointments/" + appointment.getPatient().getId();
    }

    @GetMapping("appointments/delete/{id}")
    public String deleteAppointment(@PathVariable Integer id) {
        Appointment appointment = appointmentService.getAppointmentById(id);
        Patient patient = appointment.getPatient();
        appointmentService.deleteAppointment(id);
        return "redirect:/appointments/" + patient.getId();
    }
}

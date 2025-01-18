package com.example.vallerydental.controller;

import com.example.vallerydental.model.Appointment;
import com.example.vallerydental.model.Dentist;
import com.example.vallerydental.model.Patient;
import com.example.vallerydental.model.User;
import com.example.vallerydental.service.AppointmentService;
import com.example.vallerydental.service.DentistService;
import com.example.vallerydental.service.PatientService;
import com.example.vallerydental.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class AppointmentController {
    private final AppointmentService appointmentService;
    private final DentistService dentistService;
    private final PatientService patientService;
    private final UserService userService;

    public AppointmentController(AppointmentService appointmentService, DentistService dentistService, PatientService patientService, UserService userService) {
        this.appointmentService = appointmentService;
        this.dentistService = dentistService;
        this.patientService = patientService;
        this.userService = userService;
    }

    @GetMapping("/user/appointments/{username}")
    public String getPatientAppointments(@PathVariable("username") String username, Model model) {
        User user = userService.findByUsername(username);
        Patient patient = patientService.findByUser(user);
        List<Appointment> appointments = appointmentService.getAppointmentsForPatient(patient.getId());
        model.addAttribute("patient", patient);
        model.addAttribute("appointments", appointments);
        return "Appointment/patientAppointments";
    }

    @GetMapping("/appointments/new/{id}")
    public String showCreateAppointmentForm(@PathVariable Integer id, Model model) {
        Appointment appointment = new Appointment();
        Patient patient = patientService.getPatientById(id);
        List<Dentist> dentists = dentistService.getAllDentist();
        appointment.setPatient(patient);
        model.addAttribute("dentists", dentists);
        model.addAttribute("patient", patient);
        model.addAttribute("appointment", appointment);
        return "Appointment/createAppointment";
    }

    @PostMapping("/appointments")
    public String createAppointment(@ModelAttribute("appointment") Appointment appointment) {
        appointment.setStatus("Scheduled");
        appointmentService.addAppointment(appointment);
        return "redirect:/user/appointments/" + appointment.getPatient().getUser().getUsername();
    }

    @GetMapping("/appointments/edit/{id}")
    public String showEditAppointmentForm(@PathVariable Integer id, Model model) {
        Appointment appointment = appointmentService.getAppointmentById(id);
        List<Dentist> dentists = dentistService.getAllDentist();
        model.addAttribute("dentists", dentists);
        model.addAttribute("appointment", appointment);
        return "Appointment/updateAppointment";
    }

    @PostMapping("/appointments/edit/{id}")
    public String updateAppointment(@PathVariable Integer id, @ModelAttribute("appointment") Appointment appointment) {
        appointmentService.updateAppointment(id, appointment);
        return "redirect:/user/appointments/" + appointment.getPatient().getUser().getUsername();
    }

    @GetMapping("appointments/delete/{id}")
    public String deleteAppointment(@PathVariable Integer id) {
        Appointment appointment = appointmentService.getAppointmentById(id);
        Patient patient = appointment.getPatient();
        appointmentService.deleteAppointment(id);
        return "redirect:/user/appointments/" + patient.getUser().getUsername();
    }
}

package com.example.vallerydental.controller;

import com.example.vallerydental.model.Appointment;
import com.example.vallerydental.model.Dentist;
import com.example.vallerydental.model.Person;
import com.example.vallerydental.service.impl.AppointmentServiceImpl;
import com.example.vallerydental.service.impl.DentistServiceImpl;
import com.example.vallerydental.service.impl.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.vallerydental.model.User;

import java.util.List;

@Controller
public class AdminController {
    private final AppointmentServiceImpl appointmentService;
    private final PersonServiceImpl personService;
    private final DentistServiceImpl dentistService;

    @Autowired
    public AdminController(AppointmentServiceImpl appointmentService, PersonServiceImpl personService, DentistServiceImpl dentistService) {
        this.appointmentService = appointmentService;
        this.personService = personService;
        this.dentistService = dentistService;
    }

    @GetMapping("/admin/dashboard")
    public String showDashboard(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("username", user.getUsername());

        return "Admin/Dashboard";
    }

    @GetMapping("/admin/appointments")
    public String showAppointments(@AuthenticationPrincipal User user, Model model) {
        List<Appointment> appointments = appointmentService.getCurrentAppointments();

        model.addAttribute("appointments", appointments);
        model.addAttribute("username", user.getUsername());

        return "Admin/Appointments";
    }

    @GetMapping("/admin/history")
    public String showHistory(@AuthenticationPrincipal User user, Model model) {
        List<Appointment> appointments = appointmentService.getCompletedAppointments();

        model.addAttribute("appointments", appointments);
        model.addAttribute("username", user.getUsername());

        return "Admin/History";
    }

    @GetMapping("/admin/patients")
    public String showPatients(@AuthenticationPrincipal User user, Model model) {
        List<Person> persons = personService.getAllPatients();

        model.addAttribute("patients", persons);
        model.addAttribute("username", user.getUsername());

        return "Admin/Patients";
    }

    @GetMapping("/admin/dentists")
    public String showDentists(@AuthenticationPrincipal User user, Model model) {
        List<Dentist> dentists = dentistService.getAllDentist();

        model.addAttribute("dentists", dentists);
        model.addAttribute("username", user.getUsername());

        return "Admin/Dentists";
    }
}

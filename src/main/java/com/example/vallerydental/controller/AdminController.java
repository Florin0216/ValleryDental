package com.example.vallerydental.controller;

import com.example.vallerydental.model.Appointment;
import com.example.vallerydental.model.Dentist;
import com.example.vallerydental.model.Person;
import com.example.vallerydental.service.impl.AppointmentServiceImpl;
import com.example.vallerydental.service.impl.DentistServiceImpl;
import com.example.vallerydental.service.impl.PersonServiceImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.vallerydental.model.User;

import java.util.List;

@Controller
public class AdminController {

    private final AppointmentServiceImpl appointmentServiceImpl;
    private final PersonServiceImpl personServiceImpl;
    private final DentistServiceImpl dentistServiceImpl;

    public AdminController(AppointmentServiceImpl appointmentServiceImpl, PersonServiceImpl personServiceImpl, DentistServiceImpl dentistServiceImpl) {
        this.appointmentServiceImpl = appointmentServiceImpl;
        this.personServiceImpl = personServiceImpl;
        this.dentistServiceImpl = dentistServiceImpl;
    }

    @GetMapping("/admin/dashboard")
    public String showDashboard(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("username", user.getUsername());

        return "Admin/Dashboard";
    }

    @GetMapping("/admin/appointments")
    public String showAppointments(@AuthenticationPrincipal User user, Model model) {
        List<Appointment> appointments = appointmentServiceImpl.getCurrentAppointments();

        model.addAttribute("appointments", appointments);
        model.addAttribute("username", user.getUsername());

        return "Admin/Appointments";
    }

    @GetMapping("/admin/history")
    public String showHistory(@AuthenticationPrincipal User user, Model model) {
        List<Appointment> appointments = appointmentServiceImpl.getCompletedAppointments();

        model.addAttribute("appointments", appointments);
        model.addAttribute("username", user.getUsername());

        return "Admin/History";
    }

    @GetMapping("/admin/patients")
    public String showPatients(@AuthenticationPrincipal User user, Model model) {
        List<Person> persons = personServiceImpl.getAllPatients();

        model.addAttribute("patients", persons);
        model.addAttribute("username", user.getUsername());

        return "Admin/Patients";
    }

    @GetMapping("/admin/dentists")
    public String showDentists(@AuthenticationPrincipal User user, Model model) {
        List<Dentist> dentists = dentistServiceImpl.getAllDentist();

        model.addAttribute("dentists", dentists);
        model.addAttribute("username", user.getUsername());

        return "Admin/Dentists";
    }
}

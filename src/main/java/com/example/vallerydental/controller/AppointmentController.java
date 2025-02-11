package com.example.vallerydental.controller;

import com.example.vallerydental.model.Appointment;
import com.example.vallerydental.model.Dentist;
import com.example.vallerydental.model.Person;
import com.example.vallerydental.model.User;
import com.example.vallerydental.service.impl.AppointmentServiceImpl;
import com.example.vallerydental.service.impl.DentistServiceImpl;
import com.example.vallerydental.service.impl.PersonServiceImpl;
import com.example.vallerydental.service.impl.UserServiceImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class AppointmentController {
    private final AppointmentServiceImpl appointmentServiceImpl;
    private final DentistServiceImpl dentistServiceImpl;
    private final PersonServiceImpl personServiceImpl;
    private final UserServiceImpl userService;

    public AppointmentController(AppointmentServiceImpl appointmentServiceImpl,
                                 DentistServiceImpl dentistServiceImpl,
                                 PersonServiceImpl personServiceImpl,
                                 UserServiceImpl userService) {

        this.appointmentServiceImpl = appointmentServiceImpl;
        this.dentistServiceImpl = dentistServiceImpl;
        this.personServiceImpl = personServiceImpl;
        this.userService = userService;
    }

    @GetMapping("/user/appointments/{id}")
    public String getPatientAppointments(@PathVariable("id") Integer id, Model model) {
        Optional<User> user = userService.findById(id);
        Person person = personServiceImpl.findByUser(user.orElse(null));
        List<Appointment> appointments = appointmentServiceImpl.getAppointmentsForPatient(person.getPersonID());

        model.addAttribute("person", person);
        model.addAttribute("username", user.get().getUsername());
        model.addAttribute("appointments", appointments);

        return "Appointment/patientAppointments";
    }

    @GetMapping("/appointments/availability")
    public String showCheckAvailabilityForm(Model model) {
        List<Dentist> dentists = dentistServiceImpl.getAllDentist();
        model.addAttribute("dentists", dentists);
        model.addAttribute("date", LocalDate.now());
        return "Appointment/checkAvailability";
    }

    @PostMapping("/appointments/availability")
    public String checkAvailability(
            @RequestParam Integer dentistId,
            @RequestParam LocalDate date) {

        return "redirect:/appointments/new/?dentistId=" + dentistId + "&date=" + date;
    }

    @GetMapping("/appointments/new/")
    public String showCreateAppointmentForm(
            @RequestParam(required = false) LocalDate date,
            @RequestParam(required = false) Integer dentistId,
            @AuthenticationPrincipal User user,
            Model model) {

        Appointment appointment = new Appointment();
        List<Dentist> dentists = dentistServiceImpl.getAllDentist();
        Dentist selectedDentist = dentistServiceImpl.getDentistById(dentistId);
        List<String> availableHours = appointmentServiceImpl.getAvailableHours(date, selectedDentist);

        model.addAttribute("dentists", dentists);
        model.addAttribute("appointment", appointment);
        model.addAttribute("username", user.getUsername());
        model.addAttribute("defaultAvailableHours", availableHours);
        model.addAttribute("selectedDentist", selectedDentist);
        model.addAttribute("selectedDate", date);

        return "Appointment/createAppointment";
    }

    @PostMapping("/appointments")
    public String createAppointment(@ModelAttribute("appointment") Appointment appointment,
                                    @RequestParam Integer dentistId,
                                    @RequestParam LocalDate appointmentDate,
                                    @AuthenticationPrincipal User user) {

        Dentist selectedDentist = dentistServiceImpl.getDentistById(dentistId);
        Person selectedPerson = personServiceImpl.findByUser(user);
        appointmentServiceImpl.addAppointment(selectedDentist, selectedPerson, appointmentDate, appointment);

        return "redirect:/user/appointments/" + appointment.getPerson().getUser().getId();
    }

    @GetMapping("/appointments/edit/{id}")
    public String showEditAppointmentForm(@PathVariable Integer id,@AuthenticationPrincipal User user, Model model) {

        Appointment appointment = appointmentServiceImpl.getAppointmentById(id);
        List<Dentist> dentists = dentistServiceImpl.getAllDentist();

        model.addAttribute("dentists", dentists);
        model.addAttribute("appointment", appointment);
        model.addAttribute("username", user.getUsername());

        return "Appointment/updateAppointment";
    }

    @PostMapping("/appointments/edit/{id}")
    public String updateAppointment(@PathVariable Integer id, @ModelAttribute("appointment") Appointment appointment) {
        appointmentServiceImpl.updateAppointment(id, appointment);

        return "redirect:/user/appointments/" + appointment.getPerson().getUser().getId();
    }

    @GetMapping("/appointments/delete/{id}")
    public String deleteAppointment(@PathVariable Integer id) {
        Appointment appointment = appointmentServiceImpl.getAppointmentById(id);
        Person person = appointment.getPerson();
        appointmentServiceImpl.deleteAppointment(id);

        return "redirect:/user/appointments/" + person.getUser().getId();
    }
}

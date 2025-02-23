package com.example.vallerydental.controller;

import com.example.vallerydental.model.Appointment;
import com.example.vallerydental.model.Dentist;
import com.example.vallerydental.model.Person;
import com.example.vallerydental.model.User;
import com.example.vallerydental.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/user/{userId}/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;
    private final DentistService dentistService;
    private final PersonService personService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService,
                                 DentistService dentistService,
                                 PersonService personService) {

        this.appointmentService = appointmentService;
        this.dentistService = dentistService;
        this.personService = personService;
    }

    @GetMapping("")
    public String getPatientAppointments(@PathVariable("userId") Integer userId,
                                         @RequestParam(name = "status", defaultValue = "Scheduled") String status,
                                         @AuthenticationPrincipal User user,
                                         Model model) {
        Person person = personService.findByUser(user);
        List<Appointment> appointments = appointmentService.getAppointmentsByIdAndStatus(person, status);

        model.addAttribute("person", person);
        model.addAttribute("username", user.getUsername());
        model.addAttribute("appointments", appointments);
        model.addAttribute("status", status);
        model.addAttribute("userId", userId);

        return "Appointment/patientAppointments";
    }

    @GetMapping("/availability")
    public String showCheckAvailabilityForm(@PathVariable("userId") Integer userId,
                                            @AuthenticationPrincipal User user,
                                            Model model) {
        List<Dentist> dentists = dentistService.getAllDentist();

        model.addAttribute("username", user.getUsername());
        model.addAttribute("dentists", dentists);
        model.addAttribute("date", LocalDate.now());

        return "Appointment/checkAvailability";
    }

    @PostMapping("/availability")
    public String checkAvailability(
            @PathVariable("userId") Integer userId,
            @RequestParam Integer dentistId,
            @RequestParam LocalDate date) {

        return "redirect:/user/{userId}/appointments/new?dentistId=" + dentistId + "&date=" + date;
    }

    @GetMapping("/new")
    public String showCreateAppointmentForm(
            @PathVariable("userId") Integer userId,
            @RequestParam(required = false) LocalDate date,
            @RequestParam(required = false) Integer dentistId,
            @AuthenticationPrincipal User user,
            Model model) {

        Appointment appointment = new Appointment();
        List<Dentist> dentists = dentistService.getAllDentist();
        Dentist selectedDentist = dentistService.getDentistById(dentistId);
        List<String> availableHours = appointmentService.getAvailableHours(date, selectedDentist);

        model.addAttribute("dentists", dentists);
        model.addAttribute("appointment", appointment);
        model.addAttribute("username", user.getUsername());
        model.addAttribute("defaultAvailableHours", availableHours);
        model.addAttribute("selectedDentist", selectedDentist);
        model.addAttribute("selectedDate", date);

        return "Appointment/createAppointment";
    }

    @PostMapping("/new")
    public String createAppointment(@PathVariable("userId") Integer userId,
                                    @ModelAttribute("appointment") Appointment appointment,
                                    @RequestParam Integer dentistId,
                                    @RequestParam LocalDate appointmentDate,
                                    @AuthenticationPrincipal User user) {

        Dentist selectedDentist = dentistService.getDentistById(dentistId);
        Person selectedPerson = personService.findByUser(user);

        appointmentService.addAppointment(selectedDentist, selectedPerson, appointmentDate, appointment);

        return "redirect:/user/" + appointment.getPerson().getUser().getId() + "/appointments";
    }

    @GetMapping("/{appointmentId}/edit")
    public String showEditAppointmentForm(@PathVariable("userId") Integer userId,
                                          @PathVariable("appointmentId") Integer appointmentId,
                                          @AuthenticationPrincipal User user,
                                          Model model) {
        Appointment appointment = appointmentService.getAppointmentById(appointmentId);
        List<Dentist> dentists = dentistService.getAllDentist();

        model.addAttribute("dentists", dentists);
        model.addAttribute("appointment", appointment);
        model.addAttribute("username", user.getUsername());

        return "Appointment/updateAppointment";
    }

    @PostMapping("/{appointmentId}/edit")
    public String updateAppointment(@PathVariable("userId") Integer userId,
                                    @PathVariable("appointmentId") Integer appointmentId,
                                    @ModelAttribute("appointment") Appointment appointment) {
        Appointment existingAppointment = appointmentService.getAppointmentById(appointmentId);
        appointmentService.updateAppointment(appointmentId, appointment);

        return "redirect:/user/" + existingAppointment.getPerson().getUser().getId() + "/appointments";
    }

    @GetMapping("/{appointmentId}/delete")
    public String deleteAppointment(@PathVariable("userId") Integer userId,
                                    @PathVariable("appointmentId") Integer appointmentId) {
        Appointment appointment = appointmentService.getAppointmentById(appointmentId);
        Person person = appointment.getPerson();

        appointmentService.deleteAppointment(appointmentId);

        return "redirect:/user/appointments/" + person.getUser().getId();
    }
}

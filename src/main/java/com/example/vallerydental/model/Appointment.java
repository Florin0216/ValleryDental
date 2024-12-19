package com.example.vallerydental.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointmentid", nullable = false)
    private Integer id;

    @Column(name = "appointmentdate", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate appointmentdate;

    @Column(name = "status", length = 20)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "patientid", nullable = false)
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "dentistid", nullable = false)
    private Dentist dentist;

    @ManyToMany
    @JoinTable(
            name = "Performs",
            joinColumns = @JoinColumn(name = "appointmentID"),
            inverseJoinColumns = @JoinColumn(name = "treatmentID")
    )
    private List<Treatment> treatments;

    @ManyToMany
    @JoinTable(
            name = "Assists",
            joinColumns = @JoinColumn(name = "appointmentID"),
            inverseJoinColumns = @JoinColumn(name = "staffID")
    )
    private List<Staff> assistants;

    public Dentist getDentist() {
        return dentist;
    }

    public void setDentist(Dentist dentist) {
        this.dentist = dentist;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getAppointmentdate() {
        return appointmentdate;
    }

    public void setAppointmentdate(LocalDate appointmentdate) {
        this.appointmentdate = appointmentdate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

package com.example.vallerydental.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Appointment {
    @Id
    @Column(name = "appointmentid", nullable = false, length = 10)
    private String appointmentid;

    @Column(name = "appointmentdate")
    private LocalDate appointmentdate;

    @Column(name = "status", length = 20)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cnppatient", nullable = false)
    private Patient cnppatient;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cnpdentist", nullable = false)
    private Dentist cnpdentist;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "roomid", nullable = false)
    private Room roomid;

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
            inverseJoinColumns = @JoinColumn(name = "cnpStaff")
    )
    private List<Staff> assistants;

    public Room getRoomid() {
        return roomid;
    }

    public void setRoomid(Room roomid) {
        this.roomid = roomid;
    }

    public Dentist getCnpdentist() {
        return cnpdentist;
    }

    public void setCnpdentist(Dentist cnpdentist) {
        this.cnpdentist = cnpdentist;
    }

    public Patient getCnppatient() {
        return cnppatient;
    }

    public void setCnppatient(Patient cnppatient) {
        this.cnppatient = cnppatient;
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

    public String getAppointmentid() {
        return appointmentid;
    }

    public void setAppointmentid(String appointmentid) {
        this.appointmentid = appointmentid;
    }
}

package com.example.vallerydental.model;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Entity
public class Patient {
    @Id
    @Column(name = "patientid", nullable = false, length = 13)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String patientId;

    @Column(name = "firstname", nullable = false, length = 50)
    private String firstName;

    @Column(name = "lastname", nullable = false, length = 50)
    private String lastName;

    @Column(name = "birthdate", nullable = false)
    private LocalDate birthDate;

    @Column(name = "gender", length = Integer.MAX_VALUE)
    private String gender;

    @Column(name = "phone", length = 15)
    private String phone;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "address", length = 100)
    private String address;

    @Column(name = "insurance")
    private Boolean insurance;

    @ColumnDefault("CURRENT_DATE")
    @Column(name = "registrationdate")
    private LocalDate registrationDate;

    public LocalDate getRegistrationdate() {
        return registrationDate;
    }

    public void setRegistrationdate(LocalDate registrationdate) {
        this.registrationDate = registrationdate;
    }

    public Boolean getInsurance() {
        return insurance;
    }

    public void setInsurance(Boolean insurance) {
        this.insurance = insurance;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthdate() {
        return birthDate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthDate = birthdate;
    }

    public String getLastname() {
        return lastName;
    }

    public void setLastname(String lastname) {
        this.lastName = lastname;
    }

    public String getFirstname() {
        return firstName;
    }

    public void setFirstname(String firstname) {
        this.firstName = firstname;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientid) {
        this.patientId = patientid;
    }

}

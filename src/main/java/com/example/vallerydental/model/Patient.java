package com.example.vallerydental.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Patient {
    @Id
    @Column(name = "cnppatient", nullable = false, length = 13)
    private String cnppatient;

    @Column(name = "firstname", nullable = false, length = 50)
    private String firstname;

    @Column(name = "lastname", nullable = false, length = 50)
    private String lastname;

    @Column(name = "gender", length = Integer.MAX_VALUE)
    private String gender;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "dateofbirth")
    private LocalDate dateofbirth;

    public LocalDate getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(LocalDate dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getCnppatient() {
        return cnppatient;
    }

    public void setCnppatient(String cnppatient) {
        this.cnppatient = cnppatient;
    }
}

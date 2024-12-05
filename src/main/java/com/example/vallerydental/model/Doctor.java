package com.example.vallerydental.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Doctor {
    @Id
    @Column(name = "doctorid", nullable = false, length = 13)
    private String doctorId;

    @Column(name = "firstname", nullable = false, length = 50)
    private String firstName;

    @Column(name = "lastname", nullable = false, length = 50)
    private String lastName;

    @Column(name = "specialization", length = 50)
    private String specialization;

    @Column(name = "phone", length = 15)
    private String phone;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "room", length = 10)
    private String room;

    @Column(name = "professionalrank", length = 30)
    private String professionalRank;

    @Column(name = "hiredate")
    private LocalDate hireDate;

    @Column(name = "salary")
    private Float salary;

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public LocalDate getHiredate() {
        return hireDate;
    }

    public void setHiredate(LocalDate hiredate) {
        this.hireDate = hiredate;
    }

    public String getProfessionalrank() {
        return professionalRank;
    }

    public void setProfessionalrank(String professionalrank) {
        this.professionalRank = professionalrank;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
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

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
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

    public String getDoctorid() {
        return doctorId;
    }

    public void setDoctorid(String doctorid) {
        this.doctorId = doctorid;
    }
}

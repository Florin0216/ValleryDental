package com.example.vallerydental.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Staff {
    @Id
    @Column(name = "cnpstaff", nullable = false, length = 13)
    private String cnpstaff;

    @Column(name = "firstname", nullable = false, length = 50)
    private String firstname;

    @Column(name = "lastname", nullable = false, length = 50)
    private String lastname;

    @Column(name = "gender", length = Integer.MAX_VALUE)
    private String gender;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "staffrole", length = 20)
    private String staffrole;

    @ManyToMany(mappedBy = "assistants")
    private List<Appointment> appointments;

    public String getStaffrole() {
        return staffrole;
    }

    public void setStaffrole(String staffrole) {
        this.staffrole = staffrole;
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

    public String getCnpstaff() {
        return cnpstaff;
    }

    public void setCnpstaff(String cnpstaff) {
        this.cnpstaff = cnpstaff;
    }
}

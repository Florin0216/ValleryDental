package com.example.vallerydental.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "receiptid", nullable = false)
    private Integer id;

    @Column(name = "totalcost")
    private BigDecimal totalcost;

    @Column(name = "issueddate")
    private LocalDate issueddate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "patientid", nullable = false)
    private Patient patient;

    @ManyToMany
    @JoinTable(
            name = "Invoiced",
            joinColumns = @JoinColumn(name = "receiptID"),
            inverseJoinColumns = @JoinColumn(name = "treatmentID")
    )
    private List<Treatment> treatments;

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalDate getIssueddate() {
        return issueddate;
    }

    public void setIssueddate(LocalDate issueddate) {
        this.issueddate = issueddate;
    }

    public BigDecimal getTotalcost() {
        return totalcost;
    }

    public void setTotalcost(BigDecimal totalcost) {
        this.totalcost = totalcost;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

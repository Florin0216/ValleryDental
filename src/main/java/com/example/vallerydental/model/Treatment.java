package com.example.vallerydental.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "Treatment")
public class Treatment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "treatment_id", nullable = false)
    private Integer id;

    @Column(name = "treatment_name", nullable = false, length = 50)
    private String treatmentName;

    @Column(name = "treatment_description", length = Integer.MAX_VALUE)
    private String treatmentDescription;

    @Column(name = "price")
    private BigDecimal price;

    @ManyToMany(mappedBy = "treatments")
    private List<Appointment> appointments;

    @ManyToMany(mappedBy = "treatments")
    private List<Receipt> receipts;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getTreatmentDescription() {
        return treatmentDescription;
    }

    public void setTreatmentDescription(String treatmentDescription) {
        this.treatmentDescription = treatmentDescription;
    }

    public String getTreatmentName() {
        return treatmentName;
    }

    public void setTreatmentName(String treatmentName) {
        this.treatmentName = treatmentName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

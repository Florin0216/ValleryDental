package com.example.vallerydental.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
public class Treatment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "treatmentid", nullable = false)
    private Integer id;

    @Column(name = "treatmentname", nullable = false, length = 50)
    private String treatmentname;

    @Column(name = "treatmentdescription", length = Integer.MAX_VALUE)
    private String treatmentdescription;

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

    public String getTreatmentdescription() {
        return treatmentdescription;
    }

    public void setTreatmentdescription(String treatmentdescription) {
        this.treatmentdescription = treatmentdescription;
    }

    public String getTreatmentname() {
        return treatmentname;
    }

    public void setTreatmentname(String treatmentname) {
        this.treatmentname = treatmentname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

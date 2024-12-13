package com.example.vallerydental.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.math.BigDecimal;
import java.util.List;

@Entity
public class Treatment {
    @Id
    @Column(name = "treatmentid", nullable = false, length = 10)
    private String treatmentid;

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

    public String getTreatmentid() {
        return treatmentid;
    }

    public void setTreatmentid(String treatmentid) {
        this.treatmentid = treatmentid;
    }
}

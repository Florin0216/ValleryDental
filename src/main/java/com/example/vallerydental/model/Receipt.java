package com.example.vallerydental.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Receipt {
    @Id
    @Column(name = "receiptid", nullable = false, length = 10)
    private String receiptid;

    @Column(name = "totalcost")
    private BigDecimal totalcost;

    @Column(name = "issueddate")
    private LocalDate issueddate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cnppatient", nullable = false)
    private Patient cnppatient;

    @ManyToMany
    @JoinTable(
            name = "Invoiced",
            joinColumns = @JoinColumn(name = "receiptID"),
            inverseJoinColumns = @JoinColumn(name = "treatmentID")
    )
    private List<Treatment> treatments;

    public Patient getCnppatient() {
        return cnppatient;
    }

    public void setCnppatient(Patient cnppatient) {
        this.cnppatient = cnppatient;
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

    public String getReceiptid() {
        return receiptid;
    }

    public void setReceiptid(String receiptid) {
        this.receiptid = receiptid;
    }
}

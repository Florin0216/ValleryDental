package com.example.vallerydental.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Equipment {
    @Id
    @Column(name = "equipmentid", nullable = false, length = 10)
    private String equipmentid;

    @Column(name = "equipmentname", nullable = false, length = 50)
    private String equipmentname;

    @Column(name = "equipmentdescription", length = 100)
    private String equipmentdescription;

    @Column(name = "purchasedate")
    private LocalDate purchasedate;

    @ManyToMany(mappedBy = "equipments")
    private List<Room> rooms;

    public LocalDate getPurchasedate() {
        return purchasedate;
    }

    public void setPurchasedate(LocalDate purchasedate) {
        this.purchasedate = purchasedate;
    }

    public String getEquipmentdescription() {
        return equipmentdescription;
    }

    public void setEquipmentdescription(String equipmentdescription) {
        this.equipmentdescription = equipmentdescription;
    }

    public String getEquipmentname() {
        return equipmentname;
    }

    public void setEquipmentname(String equipmentname) {
        this.equipmentname = equipmentname;
    }

    public String getEquipmentid() {
        return equipmentid;
    }

    public void setEquipmentid(String equipmentid) {
        this.equipmentid = equipmentid;
    }
}

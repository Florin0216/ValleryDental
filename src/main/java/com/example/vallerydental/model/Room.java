package com.example.vallerydental.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roomid", nullable = false)
    private Integer id;

    @Column(name = "roomname", nullable = false, length = 50)
    private String roomname;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "roomfloor")
    private Integer roomfloor;

    @ManyToMany
    @JoinTable(
            name = "Uses",
            joinColumns = @JoinColumn(name = "roomID"),
            inverseJoinColumns = @JoinColumn(name = "dentistID")
    )
    private List<Dentist> dentists;

    @ManyToMany
    @JoinTable(
            name = "Holds",
            joinColumns = @JoinColumn(name = "roomID"),
            inverseJoinColumns = @JoinColumn(name = "equipmentID")
    )
    private List<Equipment> equipments;

    public Integer getRoomfloor() {
        return roomfloor;
    }

    public void setRoomfloor(Integer roomfloor) {
        this.roomfloor = roomfloor;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

package com.example.vallerydental.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id", nullable = false)
    private Integer id;

    @Column(name = "room_name", nullable = false, length = 50)
    private String roomName;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "room_floor")
    private Integer roomFloor;

    @ManyToMany
    @JoinTable(
            name = "Room_Dentist",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "dentist_id")
    )
    private List<Dentist> dentists;

    @ManyToMany
    @JoinTable(
            name = "Room_Equipment",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "equipment_id")
    )
    private List<Equipment> equipments;

    public Integer getRoomFloor() {
        return roomFloor;
    }

    public void setRoomFloor(Integer roomFloor) {
        this.roomFloor = roomFloor;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

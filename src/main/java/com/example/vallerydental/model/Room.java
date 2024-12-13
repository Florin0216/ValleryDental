package com.example.vallerydental.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Room {
    @Id
    @Column(name = "roomid", nullable = false, length = 3)
    private String roomid;

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

    public String getRoomid() {
        return roomid;
    }

    public void setRoomid(String roomid) {
        this.roomid = roomid;
    }
}

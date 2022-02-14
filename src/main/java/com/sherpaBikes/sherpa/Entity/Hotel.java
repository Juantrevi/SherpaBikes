package com.sherpaBikes.sherpa.Entity;
import javax.persistence.*;
import java.util.List;


@Entity
@Table
public class Hotel {
    @Id
    @GeneratedValue()
    private Long id;

    private String name;
    private String location;

    @OneToOne
    private Picture picture;

    @OneToMany
    private List<Bike> bikeList;

    @OneToMany
    private List<Turist> turistList;

    public Hotel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public List<Bike> getBikeList() {
        return bikeList;
    }

    public void setBikeList(List<Bike> bikeList) {
        this.bikeList = bikeList;
    }

    public List<Turist> getTuristList() {
        return turistList;
    }

    public void setTuristList(List<Turist> turistList) {
        this.turistList = turistList;
    }
}
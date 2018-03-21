package com.sda.library.model;

import javax.persistence.*;

@Entity
@Table(name = "publishers")
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // W tym przypadku kolumny beda generowane automatycznie 'GenerationType.IDENTITY',
    // IDENTITY bedzie generowane autmoatycznie przez baze danych
    @Column(name = "publisher_id")
    private Integer publisherID;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "location")
    private String location;

    public Publisher() {
    }

    public Publisher(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public Integer getPublisherID() {
        return publisherID;
    }

    public void setPublisherID(Integer publisherID) {
        this.publisherID = publisherID;
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
}

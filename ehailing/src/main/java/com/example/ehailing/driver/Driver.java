package com.example.ehailing.driver;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class Driver {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String carModel;
    private int carCapacity;
    private double latitude;
    private double longitude;
    private boolean available;

    public Driver(String name, String carModel, int carCapacity, double latitude, double longitude, boolean available) {
        this.name = name;
        this.carModel = carModel;
        this.carCapacity = carCapacity;
        this.latitude = latitude;
        this.longitude = longitude;
        this.available = available;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", carModel='" + carModel + '\'' +
                ", carCapacity=" + carCapacity +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", available=" + available +
                '}';
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public int getCarCapacity() {
        return carCapacity;
    }

    public void setCarCapacity(int carCapacity) {
        this.carCapacity = carCapacity;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

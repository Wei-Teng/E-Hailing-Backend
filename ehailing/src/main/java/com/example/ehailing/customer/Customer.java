package com.example.ehailing.customer;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.time.LocalDateTime;
import java.util.List;

@Node
public class Customer {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private LocalDateTime expectedArrivalDateTime;
    private int passengerNum;
    private double pickUpLatitude;
    private double pickUpLongitude;
    private double destinationLatitude;
    private double destinationLongitude;
    private String status;
    @Relationship(type = "CONNECT_TO")
    private List<ConnectTo> connectToList;

    public Customer(String name, LocalDateTime expectedArrivalDateTime, int passengerNum,
                    double pickUpLatitude, double pickUpLongitude,
                    double destinationLatitude, double destinationLongitude, String status) {
        this.name = name;
        this.expectedArrivalDateTime = expectedArrivalDateTime;
        this.passengerNum = passengerNum;
        this.pickUpLatitude = pickUpLatitude;
        this.pickUpLongitude = pickUpLongitude;
        this.destinationLatitude = destinationLatitude;
        this.destinationLongitude = destinationLongitude;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", expectedArrivalDateTime=" + expectedArrivalDateTime +
                ", passengerNum=" + passengerNum +
                ", pickUpLatitude=" + pickUpLatitude +
                ", pickUpLongitude=" + pickUpLongitude +
                ", destinationLatitude=" + destinationLatitude +
                ", destinationLongitude=" + destinationLongitude +
                ", status='" + status + '\'' +
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

    public double getPickUpLatitude() {
        return pickUpLatitude;
    }

    public void setPickUpLatitude(double pickUpLatitude) {
        this.pickUpLatitude = pickUpLatitude;
    }

    public double getPickUpLongitude() {
        return pickUpLongitude;
    }

    public void setPickUpLongitude(double pickUpLongitude) {
        this.pickUpLongitude = pickUpLongitude;
    }

    public double getDestinationLatitude() {
        return destinationLatitude;
    }

    public void setDestinationLatitude(double destinationLatitude) {
        this.destinationLatitude = destinationLatitude;
    }

    public double getDestinationLongitude() {
        return destinationLongitude;
    }

    public void setDestinationLongitude(double destinationLongitude) {
        this.destinationLongitude = destinationLongitude;
    }

    public LocalDateTime getExpectedArrivalDateTime() {
        return expectedArrivalDateTime;
    }

    public void setExpectedArrivalDateTime(LocalDateTime expectedArrivalDateTime) {
        this.expectedArrivalDateTime = expectedArrivalDateTime;
    }

    public int getPassengerNum() {
        return passengerNum;
    }

    public void setPassengerNum(int passengerNum) {
        this.passengerNum = passengerNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ConnectTo> getConnectToList() {
        return connectToList;
    }
}

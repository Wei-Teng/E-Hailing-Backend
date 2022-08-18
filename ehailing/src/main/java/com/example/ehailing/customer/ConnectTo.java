package com.example.ehailing.customer;

import com.example.ehailing.driver.Driver;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@RelationshipProperties
public class ConnectTo {

    @RelationshipId
    @GeneratedValue
    private Long id;
    private double timeTakenPickedUpByDriverInMinute;
    private double timeTakenToReachDestinationInMinute;
    @TargetNode
    private Driver driver;

    @Override
    public String toString() {
        return "ConnectTo{" +
                "id=" + id +
                ", timeTakenPickedUpByDriverInMinute=" + timeTakenPickedUpByDriverInMinute +
                ", timeTakenToReachDestinationInMinute=" + timeTakenToReachDestinationInMinute +
                ", driver=" + driver +
                '}';
    }

    public Long getId() {
        return id;
    }

    // must put getter in the code, otherwise the attribute missing during http get method
    public double getTimeTakenPickedUpByDriverInMinute() {
        return timeTakenPickedUpByDriverInMinute;
    }

    public void setTimeTakenPickedUpByDriverInMinute(double timeTakenPickedUpByDriverInMinute) {
        this.timeTakenPickedUpByDriverInMinute = timeTakenPickedUpByDriverInMinute;
    }

    public double getTimeTakenToReachDestinationInMinute() {
        return timeTakenToReachDestinationInMinute;
    }

    public void setTimeTakenToReachDestinationInMinute(double timeTakenToReachDestinationInMinute) {
        this.timeTakenToReachDestinationInMinute = timeTakenToReachDestinationInMinute;
    }

    public Driver getDriver() {
        return driver;
    }
}

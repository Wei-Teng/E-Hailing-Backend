package com.example.ehailing.customer;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

public interface CustomerRepository extends Neo4jRepository<Customer, Long> {
    @Query("MATCH (c:Customer), (d:Driver {available: true}) " +
            "WHERE ID(c) = $customerId AND ID(d) = $driverId " +
            "CREATE (c)-[con:CONNECT_TO {timeTakenPickedUpByDriverInMinute: $2, timeTakenToReachDestinationInMinute: $3}]->(d)")
    void connectToAvailableDriver(Long customerId, Long driverId,
                                             double timeTakenPickedUpByDriverInMinute, double timeTakenToReachDestinationInMinute);

    @Query("MATCH (n:Customer {name: $0, pickUpLatitude: $1, pickUpLongitude: $2, destinationLatitude: $3, destinationLongitude: $4}) RETURN (n)")
    Customer findTargetCustomer(String name, double pickUpLatitude, double pickUpLongitude, double destinationLatitude, double destinationLongitude);

    @Query("MATCH (n) WHERE ID(n) = $0 SET n.status = $1")
    void updateCustomerStatus(Long id, String status);

    @Query("MATCH (n)-[con:CONNECT_TO]->(m) WHERE ID(n) = $0 DELETE con")
    void clearConnectToRelationshipOfTargetCustomer(Long id);

    @Query("MATCH (n:Customer)-[con:CONNECT_TO]->(m:Driver) WHERE ID(n) = $0 AND ID(m) <> $1 DELETE con")
    void removeUnusualConnectionOfTargetCustomer(Long customerId, Long driverIdThatNeedRemainConnection);
}

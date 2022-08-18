package com.example.ehailing.driver;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

public interface DriverRepository extends Neo4jRepository<Driver, Long> {
    @Query("MATCH (n) WHERE ID(n) = $0 SET n.available = $1")
    void updateDriverAvailability(Long id, boolean available);

    @Query("MATCH (n:Customer)-[con:CONNECT_TO]->(m:Driver) WHERE ID(m) = $1 AND ID(n) <> $0 DELETE con")
    void removeUnusualConnectionOfTargetDriver(Long customerIdThatNeedRemainConnection, Long driverId);
}

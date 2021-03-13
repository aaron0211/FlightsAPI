package com.aaron.MyflightsApplication.repository;

import com.aaron.MyflightsApplication.domain.Flight;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface FlightRepository extends CrudRepository<Flight, Long> {

    Set<Flight> findAll();
    List<Flight> findByCompany(String company);
    List<Flight> findByDeparture(String departure);
    List<Flight> findByDestination(String destination);
    List<Flight> findByStopovers(int stopovers);
    void deleteByDestination(String destination);
}

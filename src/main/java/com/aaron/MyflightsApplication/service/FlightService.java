package com.aaron.MyflightsApplication.service;

import com.aaron.MyflightsApplication.domain.Flight;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface FlightService {

    Set<Flight> findAll();
    Optional<Flight> findById(long id);
    Flight addFlight(Flight flight);
    Flight modifyFlight(long id, Flight newFlight);
    void deleteFlight(long id);
    List<Flight> findByDestination(String destination);
    List<Flight> findByCompany(String company);
    List<Flight> findByDeparture(String departure);
    List<Flight> findByStopover(int stopovers);
    void deleteByDestination(String destination);
}

package com.aaron.MyflightsApplication.service;

import com.aaron.MyflightsApplication.domain.Flight;
import com.aaron.MyflightsApplication.exception.FlightNotFoundException;
import com.aaron.MyflightsApplication.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class FlightServiceImpl implements FlightService{

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private EntityManager em;

    @Override
    public Set<Flight> findAll() {
        return flightRepository.findAll();
    }

    @Override
    public Optional<Flight> findById(long id) {
        return flightRepository.findById(id);
    }

    @Override
    public Flight addFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public Flight modifyFlight(long id, Flight newFlight) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(()-> new FlightNotFoundException(id));
        newFlight.setId(flight.getId());
        return flightRepository.save(newFlight);
    }

    @Override
    public void deleteFlight(long id) {
        flightRepository.findById(id)
                .orElseThrow(()-> new FlightNotFoundException(id));
        flightRepository.deleteById(id);
    }

    @Override
    public List<Flight> findByDestination(String destination) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Flight> cq = cb.createQuery(Flight.class);
        Root<Flight> root = cq.from(Flight.class);

        cq.select(root).where(cb.equal(root.get("destination"),destination));
        List<Flight> result =  em.createQuery(cq).getResultList();
        return  result;
    }

    @Override
    public List<Flight> findByCompany(String company) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Flight> cq = cb.createQuery(Flight.class);
        Root<Flight> root = cq.from(Flight.class);

        cq.select(root).where(cb.equal(root.get("company"),company));
        return em.createQuery(cq).getResultList();
    }

    @Override
    public List<Flight> findByDeparture(String departure) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Flight> cq = cb.createQuery(Flight.class);
        Root<Flight> root = cq.from(Flight.class);

        cq.select(root).where(cb.equal(root.get("departure"),departure));
        return em.createQuery(cq).getResultList();
    }

    @Override
    public List<Flight> findByStopover(int stopovers) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Flight> cq = cb.createQuery(Flight.class);
        Root<Flight> root = cq.from(Flight.class);

        cq.select(root).where(cb.equal(root.get("stopovers"),stopovers));
        return em.createQuery(cq).getResultList();
    }

    @Override
    public void deleteByDestination(String destination) {
        //TODO eliminar todos los vuelos de un destino
    }
}

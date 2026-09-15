package com.portifolyo.airlinesystem.repository;

import com.portifolyo.airlinesystem.entity.Flight;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository extends BaseRepository<Flight> {

    List<Flight> findByDepartureCityIgnoreCaseAndArrivalCityIgnoreCaseAndDepartureTimeBetween(
            String departureCity, String arrivalCity, LocalDateTime start, LocalDateTime end);

    List<Flight> findByDepartureCityIgnoreCaseAndArrivalCityIgnoreCase(String departureCity, String arrivalCity);
}

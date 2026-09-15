package com.portifolyo.airlinesystem.repository;

import com.portifolyo.airlinesystem.entity.Seat;

import java.util.List;
import java.util.Optional;

public interface SeatRepository extends BaseRepository<Seat>{

    List<Seat> findByFlightIdAndIsBookedFalse(String flightId);
    Optional<Seat> findByIdAndIsBookedFalse(String id);
    long countByFlightIdAndIsBookedFalse(String flightId);
}

package com.portifolyo.airlinesystem.repository;

import com.portifolyo.airlinesystem.entity.Ticket;
import com.portifolyo.airlinesystem.enums.TicketStatus;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends BaseRepository<Ticket> {

    List<Ticket> findByUserId(String userId);
    Optional<Ticket> findByPnrCode(String pnrCode);
    long countByFlightIdAndStatus(String flightId, TicketStatus status);
    long countByStatus(TicketStatus status);
}

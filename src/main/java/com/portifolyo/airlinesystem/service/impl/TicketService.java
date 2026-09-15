package com.portifolyo.airlinesystem.service.impl;

import com.portifolyo.airlinesystem.dto.Request.BookTicketRequest;
import com.portifolyo.airlinesystem.dto.Response.TicketResponse;
import com.portifolyo.airlinesystem.entity.Flight;
import com.portifolyo.airlinesystem.entity.Seat;
import com.portifolyo.airlinesystem.entity.Ticket;
import com.portifolyo.airlinesystem.entity.User;
import com.portifolyo.airlinesystem.enums.PaymentStatus;
import com.portifolyo.airlinesystem.enums.TicketStatus;
import com.portifolyo.airlinesystem.exception.OperationNotPermittedException;
import com.portifolyo.airlinesystem.exception.ResourceNotFoundException;
import com.portifolyo.airlinesystem.exception.SeatNotAvaibleException;
import com.portifolyo.airlinesystem.repository.PaymentRepository;
import com.portifolyo.airlinesystem.repository.SeatRepository;
import com.portifolyo.airlinesystem.repository.TicketRepository;
import com.portifolyo.airlinesystem.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TicketService extends BaseServiceImpl<Ticket> {
    private final TicketRepository ticketRepository;
    private final SeatRepository seatRepository;
    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;
    private final FlightService flightService;

    public TicketService(TicketRepository ticketRepository, SeatRepository seatRepository, UserRepository userRepository, PaymentRepository paymentRepository, FlightService flightService) {
        super(ticketRepository);
        this.ticketRepository = ticketRepository;
        this.seatRepository = seatRepository;
        this.userRepository = userRepository;
        this.paymentRepository = paymentRepository;
        this.flightService = flightService;
    }

    @Transactional
    public Ticket bookTicket(String username, BookTicketRequest request) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Kullanıcı bulunamadı"));

        Flight flight = flightService.getFlightById(request.getFlightId());

        Seat seat = seatRepository.findByIdAndIsBookedFalse(request.getSeatId())
                .orElseThrow(() -> new SeatNotAvaibleException("Seçilen koltuk müsait değil"));

        if (!seat.getFlight().getId().equals(flight.getId())) {
            throw new SeatNotAvaibleException("Koltuk bu uçuşa ait değil");
        }

        seat.setIsBooked(true);
        seatRepository.save(seat);

        BigDecimal totalPrice = flight.getBasePrice()
                .multiply(BigDecimal.valueOf(seat.getPriceMultiplier()));

        Ticket ticket = Ticket.builder()
                .pnrCode(generatePnr())
                .user(user)
                .flight(flight)
                .seat(seat)
                .passengerFullName(request.getPassengerFullName())
                .passengerIdNumber(request.getPassengerIdNumber())
                .status(TicketStatus.BOOKED)
                .totalPrice(totalPrice)
                .build();

        return ticketRepository.save(ticket);
    }

    @Transactional
    public Ticket cancelTicket(String username, String ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new ResourceNotFoundException("Bilet bulunamadı: " + ticketId));

        if (!ticket.getUser().getUsername().equals(username)) {
            throw new OperationNotPermittedException("Bu bileti iptal etme yetkiniz yok");
        }

        if (ticket.getStatus() == TicketStatus.CANCELLED) {
            throw new SeatNotAvaibleException("Bilet zaten iptal edilmiş");
        }

        ticket.setStatus(TicketStatus.CANCELLED);

        Seat seat = ticket.getSeat();
        seat.setIsBooked(false);
        seatRepository.save(seat);

        // Eğer ödeme yapılmışsa otomatik olarak iade durumuna çek
        paymentRepository.findByTicketId(ticket.getId()).ifPresent(payment -> {
            payment.setStatus(PaymentStatus.REFUNDED);
            paymentRepository.save(payment);
        });

        return ticketRepository.save(ticket);
    }

    public List<Ticket> getUserTickets(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Kullanıcı bulunamadı"));
        return ticketRepository.findByUserId(user.getId());
    }

    public Ticket getByPnr(String pnr) {
        return ticketRepository.findByPnrCode(pnr)
                .orElseThrow(() -> new ResourceNotFoundException("Bilet bulunamadı: " + pnr));
    }

    private String generatePnr() {
        return UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }

    public TicketResponse toResponse(Ticket ticket) {
        return TicketResponse.builder()
                .id(ticket.getId())
                .pnrCode(ticket.getPnrCode())
                .flightNumber(ticket.getFlight().getFlightNumber())
                .departureCity(ticket.getFlight().getDepartureCity())
                .arrivalCity(ticket.getFlight().getArrivalCity())
                .departureTime(ticket.getFlight().getDepartureTime())
                .seatNumber(ticket.getSeat().getSeatNumber())
                .passengerFullName(ticket.getPassengerFullName())
                .bookingDate(ticket.getBookingDate())
                .status(ticket.getStatus())
                .totalPrice(ticket.getTotalPrice())
                .build();
    }

    public List<TicketResponse> toResponseList(List<Ticket> tickets) {
        return tickets.stream().map(this::toResponse).collect(Collectors.toList());
    }
}

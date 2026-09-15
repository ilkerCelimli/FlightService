package com.portifolyo.airlinesystem.service.impl;


import com.portifolyo.airlinesystem.dto.Response.SalesReportResponse;
import com.portifolyo.airlinesystem.entity.Flight;
import com.portifolyo.airlinesystem.entity.Payment;
import com.portifolyo.airlinesystem.enums.PaymentStatus;
import com.portifolyo.airlinesystem.enums.TicketStatus;
import com.portifolyo.airlinesystem.repository.FlightRepository;
import com.portifolyo.airlinesystem.repository.PaymentRepository;
import com.portifolyo.airlinesystem.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final TicketRepository ticketRepository;
    private final PaymentRepository paymentRepository;
    private final FlightRepository flightRepository;

    public SalesReportResponse generateSalesReport() {
        List<Payment> successfulPayments = paymentRepository.findByStatus(PaymentStatus.SUCCESS);

        BigDecimal totalRevenue = successfulPayments.stream()
                .map(Payment::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        long totalSold = ticketRepository.countByStatus(TicketStatus.BOOKED)
                + ticketRepository.countByStatus(TicketStatus.CHECKED_IN)
                + ticketRepository.countByStatus(TicketStatus.COMPLETED);
        long totalCancelled = ticketRepository.countByStatus(TicketStatus.CANCELLED);

        List<Flight> flights = flightRepository.findAll();

        Map<String, Long> ticketsByRoute = new LinkedHashMap<>();
        List<SalesReportResponse.TopFlight> topFlights = new ArrayList<>();

        for (Flight flight : flights) {
            String route = flight.getDepartureCity() + " -> " + flight.getArrivalCity();
            long sold = ticketRepository.countByFlightIdAndStatus(flight.getId(), TicketStatus.BOOKED)
                    + ticketRepository.countByFlightIdAndStatus(flight.getId(), TicketStatus.CHECKED_IN)
                    + ticketRepository.countByFlightIdAndStatus(flight.getId(), TicketStatus.COMPLETED);

            ticketsByRoute.merge(route, sold, Long::sum);

            BigDecimal revenue = flight.getBasePrice().multiply(BigDecimal.valueOf(sold));
            topFlights.add(SalesReportResponse.TopFlight.builder()
                    .flightNumber(flight.getFlightNumber())
                    .route(route)
                    .ticketsSold(sold)
                    .revenue(revenue)
                    .build());
        }

        topFlights = topFlights.stream()
                .sorted((a, b) -> Long.compare(b.getTicketsSold(), a.getTicketsSold()))
                .limit(10)
                .collect(Collectors.toList());

        return SalesReportResponse.builder()
                .totalRevenue(totalRevenue)
                .totalTicketsSold(totalSold)
                .totalTicketsCancelled(totalCancelled)
                .ticketsByRoute(ticketsByRoute)
                .topFlights(topFlights)
                .build();
    }
}
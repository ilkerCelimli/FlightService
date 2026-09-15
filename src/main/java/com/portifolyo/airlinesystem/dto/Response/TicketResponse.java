package com.portifolyo.airlinesystem.dto.Response;

import com.portifolyo.airlinesystem.enums.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketResponse {
    private String id;
    private String pnrCode;
    private String flightNumber;
    private String departureCity;
    private String arrivalCity;
    private LocalDateTime departureTime;
    private String seatNumber;
    private String passengerFullName;
    private LocalDateTime bookingDate;
    private TicketStatus status;
    private BigDecimal totalPrice;
}
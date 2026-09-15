package com.portifolyo.airlinesystem.dto.Response;

import com.portifolyo.airlinesystem.enums.FlightStatus;
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
public class FlightResponse {
    private String id;
    private String flightNumber;
    private String airlineName;
    private String departureAirportCode;
    private String departureCity;
    private String arrivalAirportCode;
    private String arrivalCity;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private BigDecimal basePrice;
    private Integer totalSeats;
    private Long availableSeats;
    private FlightStatus status;
}
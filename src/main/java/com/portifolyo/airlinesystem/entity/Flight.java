package com.portifolyo.airlinesystem.entity;

import com.portifolyo.airlinesystem.enums.FlightStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "flights")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Flight extends BaseEntity {

    @Column(name = "flight_number", nullable = false, length = 20)
    private String flightNumber;

    @Column(name = "airline_name", nullable = false, length = 100)
    private String airlineName;

    @Column(name = "departure_airport_code", nullable = false, length = 10)
    private String departureAirportCode;

    @Column(name = "departure_city", nullable = false, length = 100)
    private String departureCity;

    @Column(name = "arrival_airport_code", nullable = false, length = 10)
    private String arrivalAirportCode;

    @Column(name = "arrival_city", nullable = false, length = 100)
    private String arrivalCity;

    @Column(name = "departure_time", nullable = false)
    private LocalDateTime departureTime;

    @Column(name = "arrival_time", nullable = false)
    private LocalDateTime arrivalTime;

    @Column(name = "base_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal basePrice;

    @Column(name = "total_seats", nullable = false)
    private Integer totalSeats;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @Builder.Default
    private FlightStatus status = FlightStatus.SCHEDULED;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    @ToString.Exclude
    private List<Seat> seats = new ArrayList<>();

}

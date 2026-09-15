package com.portifolyo.airlinesystem.entity;

import com.portifolyo.airlinesystem.enums.SeatClass;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "seats", uniqueConstraints = @UniqueConstraint(columnNames = {"flight_id", "seat_number"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Seat extends BaseEntity {



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id", nullable = false)
    @ToString.Exclude
    private Flight flight;

    @Column(name = "seat_number", nullable = false, length = 10)
    private String seatNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "seat_class", nullable = false, length = 20)
    private SeatClass seatClass;

    @Column(name = "price_multiplier", nullable = false)
    @Builder.Default
    private Double priceMultiplier = 1.0;

    @Column(name = "is_booked", nullable = false)
    @Builder.Default
    private Boolean isBooked = false;
}
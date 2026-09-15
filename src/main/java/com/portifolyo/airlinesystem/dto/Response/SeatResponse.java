package com.portifolyo.airlinesystem.dto.Response;

import com.portifolyo.airlinesystem.entity.Seat;
import com.portifolyo.airlinesystem.enums.SeatClass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatResponse {
    private String id;
    private String seatNumber;
    private SeatClass seat;
    private Double priceMultiplier;
    private Boolean isBooked;
}

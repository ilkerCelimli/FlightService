package com.portifolyo.airlinesystem.dto.Request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/***
 * Bilet Kesme Request
 */
@Data
public class FlightRequest {

    @NotBlank(message = "Uçuş numarası boş olamaz")
    private String flightNumber;

    @NotBlank(message = "Havayolu adı boş olamaz")
    private String airlineName;

    @NotBlank(message = "Kalkış havalimanı kodu boş olamaz")
    private String departureAirportCode;

    @NotBlank(message = "Kalkış şehri boş olamaz")
    private String departureCity;

    @NotBlank(message = "Varış havalimanı kodu boş olamaz")
    private String arrivalAirportCode;

    @NotBlank(message = "Varış şehri boş olamaz")
    private String arrivalCity;

    @NotNull(message = "Kalkış zamanı boş olamaz")
    @Future(message = "Kalkış zamanı gelecekte olmalı")
    private LocalDateTime departureTime;

    @NotNull(message = "Varış zamanı boş olamaz")
    private LocalDateTime arrivalTime;

    @NotNull(message = "Fiyat boş olamaz")
    @DecimalMin(value = "0.0", inclusive = false, message = "Fiyat sıfırdan büyük olmalı")
    private BigDecimal basePrice;

    @NotNull(message = "Koltuk sayısı boş olamaz")
    @Min(value = 1, message = "En az 1 koltuk olmalı")
    private Integer totalSeats;
}
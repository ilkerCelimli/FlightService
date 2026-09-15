package com.portifolyo.airlinesystem.dto.Request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookTicketRequest {

    @NotNull(message = "Uçuş id boş olamaz")
    private String flightId;

    @NotNull(message = "Koltuk id boş olamaz")
    private String seatId;

    @NotBlank(message = "Yolcu ad soyad boş olamaz")
    private String passengerFullName;

    @NotBlank(message = "Kimlik/Pasaport numarası boş olamaz")
    private String passengerIdNumber;
}

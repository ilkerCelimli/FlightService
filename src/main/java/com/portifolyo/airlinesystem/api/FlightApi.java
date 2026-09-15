package com.portifolyo.airlinesystem.api;
import com.portifolyo.airlinesystem.dto.Request.FlightRequest;
import com.portifolyo.airlinesystem.dto.Response.ApiResponse;
import com.portifolyo.airlinesystem.dto.Response.FlightResponse;
import com.portifolyo.airlinesystem.dto.Response.SeatResponse;
import com.portifolyo.airlinesystem.entity.Flight;
import com.portifolyo.airlinesystem.repository.SeatRepository;
import com.portifolyo.airlinesystem.service.impl.FlightService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/flights")
@RequiredArgsConstructor
public class FlightApi {

    private final FlightService flightService;
    private final SeatRepository seatRepository;

    @GetMapping
    public ResponseEntity<ApiResponse<List<FlightResponse>>> getAllFlights() {
        List<FlightResponse> flights = flightService.getAllFlights().stream()
                .map(flightService::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success("Uçuşlar listelendi", flights));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<FlightResponse>>> searchFlights(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {

        List<FlightResponse> flights = flightService.searchFlights(from, to, date).stream()
                .map(flightService::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success("Arama sonuçları", flights));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<FlightResponse>> getFlight(@PathVariable String id) {
        Flight flight = flightService.getFlightById(id);
        return ResponseEntity.ok(ApiResponse.success("Uçuş bulundu", flightService.toResponse(flight)));
    }

    @GetMapping("/{id}/seats")
    public ResponseEntity<ApiResponse<List<SeatResponse>>> getAvailableSeats(@PathVariable String id) {
        List<SeatResponse> seats = seatRepository.findByFlightIdAndIsBookedFalse(id).stream()
                .map(s -> SeatResponse.builder()
                        .id(s.getId())
                        .seatNumber(s.getSeatNumber())
                        .seat(s.getSeatClass())
                        .priceMultiplier(s.getPriceMultiplier())
                        .isBooked(s.getIsBooked())
                        .build())
                .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success("Müsait koltuklar", seats));
    }

    // ---- Aşağıdaki uç noktalar sadece ROLE_ADMIN için açıktır (bkz. SecurityConfig) ----

    @PostMapping
    public ResponseEntity<ApiResponse<FlightResponse>> createFlight(@Valid @RequestBody FlightRequest request) {
        Flight flight = flightService.createFlight(request);
        return ResponseEntity.ok(ApiResponse.success("Uçuş oluşturuldu", flightService.toResponse(flight)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<FlightResponse>> updateFlight(@PathVariable String id,
                                                                    @Valid @RequestBody FlightRequest request) {
        Flight flight = flightService.updateFlight(id, request);
        return ResponseEntity.ok(ApiResponse.success("Uçuş güncellendi", flightService.toResponse(flight)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteFlight(@PathVariable String id) {
        flightService.deleteFlight(id);
        return ResponseEntity.ok(ApiResponse.success("Uçuş silindi", null));
    }
}

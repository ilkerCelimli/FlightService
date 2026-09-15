package com.portifolyo.airlinesystem.service.impl;

import com.portifolyo.airlinesystem.dto.Request.FlightRequest;
import com.portifolyo.airlinesystem.dto.Response.FlightResponse;
import com.portifolyo.airlinesystem.entity.Flight;
import com.portifolyo.airlinesystem.entity.Seat;
import com.portifolyo.airlinesystem.enums.SeatClass;
import com.portifolyo.airlinesystem.exception.ResourceNotFoundException;
import com.portifolyo.airlinesystem.repository.FlightRepository;
import com.portifolyo.airlinesystem.repository.SeatRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Service
public class FlightService extends BaseServiceImpl<Flight> {


    private final FlightRepository flightRepository;
    private final SeatRepository seatRepository;

    public FlightService(FlightRepository flightRepository, SeatRepository seatRepository) {
        super(flightRepository);
        this.flightRepository = flightRepository;
        this.seatRepository = seatRepository;
    }

    public Flight createFlight(FlightRequest request) {
        Flight flight = Flight.builder()
                .flightNumber(request.getFlightNumber())
                .airlineName(request.getAirlineName())
                .departureAirportCode(request.getDepartureAirportCode())
                .departureCity(request.getDepartureCity())
                .arrivalAirportCode(request.getArrivalAirportCode())
                .arrivalCity(request.getArrivalCity())
                .departureTime(request.getDepartureTime())
                .arrivalTime(request.getArrivalTime())
                .basePrice(request.getBasePrice())
                .totalSeats(request.getTotalSeats())
                .build();

        Flight saved = flightRepository.save(flight);
        generateSeats(saved);
        return saved;
    }

    /**
     * Uçuş oluşturulduğunda koltuk haritasını otomatik üretir.
     * Yaklaşık: ilk %5 First Class, sonraki %10 Business, kalanı Economy.
     */
    private void generateSeats(Flight flight) {
        List<Seat> seats = new ArrayList<>();
        int total = flight.getTotalSeats();
        int firstCount = Math.max(1, total / 20);
        int businessCount = Math.max(1, total / 10);

        for (int i = 1; i <= total; i++) {
            SeatClass seatClass;
            double multiplier;

            if (i <= firstCount) {
                seatClass = SeatClass.FIRST_CLASS;
                multiplier = 3.0;
            } else if (i <= firstCount + businessCount) {
                seatClass = SeatClass.BUSINESS;
                multiplier = 1.8;
            } else {
                seatClass = SeatClass.ECONOMY;
                multiplier = 1.0;
            }

            char letter = (char) ('A' + ((i - 1) % 6));
            int rowNumber = (i - 1) / 6 + 1;

            seats.add(Seat.builder()
                    .flight(flight)
                    .seatNumber(rowNumber + "" + letter)
                    .seatClass(seatClass)
                    .priceMultiplier(multiplier)
                    .isBooked(false)
                    .build());
        }
        seatRepository.saveAll(seats);
    }

    public List<Flight> searchFlights(String departureCity, String arrivalCity, LocalDateTime date) {
        if (date != null) {
            LocalDateTime start = date.toLocalDate().atStartOfDay();
            LocalDateTime end = start.plusDays(1);
            return flightRepository.findByDepartureCityIgnoreCaseAndArrivalCityIgnoreCaseAndDepartureTimeBetween(
                    departureCity, arrivalCity, start, end);
        }
        return flightRepository.findByDepartureCityIgnoreCaseAndArrivalCityIgnoreCase(departureCity, arrivalCity);
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Flight getFlightById(String id) {
        return flightRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Uçuş bulunamadı: " + id));
    }

    public Flight updateFlight(String id, FlightRequest request) {
        Flight flight = getFlightById(id);
        flight.setFlightNumber(request.getFlightNumber());
        flight.setAirlineName(request.getAirlineName());
        flight.setDepartureAirportCode(request.getDepartureAirportCode());
        flight.setDepartureCity(request.getDepartureCity());
        flight.setArrivalAirportCode(request.getArrivalAirportCode());
        flight.setArrivalCity(request.getArrivalCity());
        flight.setDepartureTime(request.getDepartureTime());
        flight.setArrivalTime(request.getArrivalTime());
        flight.setBasePrice(request.getBasePrice());
        return flightRepository.save(flight);
    }

    public void deleteFlight(String id) {
        Flight flight = getFlightById(id);
        flightRepository.delete(flight);
    }

    public FlightResponse toResponse(Flight flight) {
        long available = seatRepository.countByFlightIdAndIsBookedFalse(flight.getId());
        return FlightResponse.builder()
                .id(flight.getId())
                .flightNumber(flight.getFlightNumber())
                .airlineName(flight.getAirlineName())
                .departureAirportCode(flight.getDepartureAirportCode())
                .departureCity(flight.getDepartureCity())
                .arrivalAirportCode(flight.getArrivalAirportCode())
                .arrivalCity(flight.getArrivalCity())
                .departureTime(flight.getDepartureTime())
                .arrivalTime(flight.getArrivalTime())
                .basePrice(flight.getBasePrice())
                .totalSeats(flight.getTotalSeats())
                .availableSeats(available)
                .status(flight.getStatus())
                .build();
    }
}

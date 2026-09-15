package com.portifolyo.airlinesystem.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesReportResponse {
    private BigDecimal totalRevenue;
    private long totalTicketsSold;
    private long totalTicketsCancelled;
    private Map<String, Long> ticketsByRoute;
    private List<TopFlight> topFlights;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class TopFlight {
        private String flightNumber;
        private String route;
        private long ticketsSold;
        private BigDecimal revenue;
    }
}
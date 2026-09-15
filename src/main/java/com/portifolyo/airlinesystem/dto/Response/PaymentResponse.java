package com.portifolyo.airlinesystem.dto.Response;

import com.portifolyo.airlinesystem.enums.PaymentMethod;
import com.portifolyo.airlinesystem.enums.PaymentStatus;
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
public class PaymentResponse {
    private String id;
    private String ticketId;
    private String pnrCode;
    private BigDecimal amount;
    private PaymentMethod method;
    private PaymentStatus status;
    private String transactionId;
    private LocalDateTime paymentDate;
}
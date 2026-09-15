package com.portifolyo.airlinesystem.api;
import com.portifolyo.airlinesystem.dto.Request.PaymentRequest;
import com.portifolyo.airlinesystem.dto.Response.PaymentResponse;
import com.portifolyo.airlinesystem.entity.Payment;
import com.portifolyo.airlinesystem.service.impl.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentApi {

    private final PaymentService paymentService;

    /**
     * Ödeme işlemi
     * POST /api/payments
     */
    @PostMapping
    public ResponseEntity<PaymentResponse> processPayment(
            @RequestHeader("X-Username") String username,
            @RequestBody PaymentRequest request) {

        Payment payment = paymentService.processPayment(username, request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(paymentService.toResponse(payment));
    }
}

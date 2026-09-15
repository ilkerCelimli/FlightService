package com.portifolyo.airlinesystem.service.impl;


import com.portifolyo.airlinesystem.dto.Request.PaymentRequest;
import com.portifolyo.airlinesystem.dto.Response.PaymentResponse;
import com.portifolyo.airlinesystem.entity.Payment;

import com.portifolyo.airlinesystem.entity.Ticket;
import com.portifolyo.airlinesystem.enums.PaymentStatus;
import com.portifolyo.airlinesystem.exception.BadRequestException;
import com.portifolyo.airlinesystem.exception.OperationNotPermittedException;
import com.portifolyo.airlinesystem.exception.ResourceNotFoundException;
import com.portifolyo.airlinesystem.repository.PaymentRepository;
import com.portifolyo.airlinesystem.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final TicketRepository ticketRepository;

    @Transactional
    public Payment processPayment(String username, PaymentRequest request) {
        Ticket ticket = ticketRepository.findById(request.getTicketId())
                .orElseThrow(() -> new ResourceNotFoundException("Bilet bulunamadı: " + request.getTicketId()));

        if (!ticket.getUser().getUsername().equals(username)) {
            throw new OperationNotPermittedException("Bu bilet için ödeme yapma yetkiniz yok");
        }

        if (paymentRepository.findByTicketId(ticket.getId()).isPresent()) {
            throw new BadRequestException("Bu bilet için ödeme zaten yapılmış");
        }

        // NOT: Bu bir demo ödeme akışıdır; gerçek bir ödeme sağlayıcısına (Stripe, iyzico vb.)
        // bağlanmaz, kart bilgileri saklanmaz veya doğrulanmaz.
        Payment payment = Payment.builder()
                .ticket(ticket)
                .amount(ticket.getTotalPrice())
                .method(request.getMethod())
                .status(PaymentStatus.SUCCESS)
                .transactionId("TXN-" + UUID.randomUUID().toString().substring(0, 12).toUpperCase())
                .build();

        return paymentRepository.save(payment);
    }

    public PaymentResponse toResponse(Payment payment) {
        return PaymentResponse.builder()
                .id(payment.getId())
                .ticketId(payment.getTicket().getId())
                .pnrCode(payment.getTicket().getPnrCode())
                .amount(payment.getAmount())
                .method(payment.getMethod())
                .status(payment.getStatus())
                .transactionId(payment.getTransactionId())
                .paymentDate(payment.getPaymentDate())
                .build();
    }
}
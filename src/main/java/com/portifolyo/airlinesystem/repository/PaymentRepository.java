package com.portifolyo.airlinesystem.repository;

import com.portifolyo.airlinesystem.entity.Payment;
import com.portifolyo.airlinesystem.enums.PaymentStatus;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends BaseRepository<Payment>{
    Optional<Payment> findByTicketId(String ticketId);
    List<Payment> findByStatus(PaymentStatus status);
}

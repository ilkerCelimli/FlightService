package com.portifolyo.airlinesystem.dto.Request;

import com.portifolyo.airlinesystem.enums.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PaymentRequest {

    @NotNull(message = "Bilet id boş olamaz")
    private String ticketId;

    @NotNull(message = "Ödeme yöntemi boş olamaz")
    private PaymentMethod method;

    // NOT: Bu alanlar demo amaçlıdır; gerçek bir ödeme sağlayıcısına gönderilmez
    // ve veritabanında saklanmaz. Gerçek bir entegrasyon için Stripe/iyzico gibi
    // bir ödeme sağlayıcısının SDK'sını kullanmanız gerekir.
    private String cardNumber;
    private String cardHolderName;
    private String expiryDate;
    private String cvv;
}
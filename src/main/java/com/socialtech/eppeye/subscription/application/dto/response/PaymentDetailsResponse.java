package com.socialtech.eppeye.subscription.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentDetailsResponse {
    private Long id;
    private SubscriptionResponse subscription;
    private String paymentMethod;
    private Double amount;
    private LocalDate paymentDate;
}

package com.socialtech.eppeye.subscription.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
public class PaymentDetailsRequest {

    @NotNull(message = "Subscription ID cannot be null")
    private Long subscriptionId;

    @NotBlank(message = "Payment method cannot be empty")
    private String paymentMethod;

    @NotNull(message = "Amount cannot be null")
    private Double amount;

    @NotNull(message = "Payment date cannot be null")
    private LocalDate paymentDate;
}

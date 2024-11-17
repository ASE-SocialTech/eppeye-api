package com.socialtech.eppeye.subscription.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlanRequest {

    @NotBlank(message = "The name must not be blank")
    private String name;

    @NotBlank(message = "The description must not be blank")
    private String description;

    @NotNull(message = "The price is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "The price must be zero or greater")
    private Double price;

    @NotNull(message = "The duration is required")
    @Positive(message = "The duration must be greater than 0")
    private Integer duration;
}

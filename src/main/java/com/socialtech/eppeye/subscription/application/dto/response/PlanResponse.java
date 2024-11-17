package com.socialtech.eppeye.subscription.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlanResponse {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer duration;
}

package com.socialtech.eppeye.configuration.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PPERequirementsRequest {
    private String type;
    private Integer quantity;
}

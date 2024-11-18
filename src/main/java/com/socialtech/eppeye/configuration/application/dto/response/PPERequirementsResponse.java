package com.socialtech.eppeye.configuration.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PPERequirementsResponse {
    private Long id;
    private String type;
    private Integer quantity;
    private String safetyStandardDescription;
}

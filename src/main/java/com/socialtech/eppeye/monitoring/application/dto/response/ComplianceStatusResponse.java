package com.socialtech.eppeye.monitoring.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ComplianceStatusResponse {

    private Long id;
    private boolean isCompliant;
    private String description;
}
package com.socialtech.eppeye.monitoring.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ComplianceStatusRequest {

    private boolean isCompliant;
    private String description;
    private Long monitoringSessionId;
}
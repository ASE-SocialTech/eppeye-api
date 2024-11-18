package com.socialtech.eppeye.monitoring.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MonitoringSessionResponse {

    private Long id;
    private Date startTime;
    private Date endTime;
    private String status;
    private List<MonitoringIncidentResponse> incidents;
    private ComplianceStatusResponse complianceStatus;
}
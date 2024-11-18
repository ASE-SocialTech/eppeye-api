package com.socialtech.eppeye.monitoring.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MonitoringIncidentRequest {

    private String incidentType;
    private Date detectedTime;
    private String description;
    private Long monitoringSessionId;
}
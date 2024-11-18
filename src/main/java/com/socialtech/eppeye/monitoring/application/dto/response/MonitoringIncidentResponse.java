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
public class MonitoringIncidentResponse {

    private Long id;
    private String incidentType;
    private Date detectedTime;
    private String description;
}

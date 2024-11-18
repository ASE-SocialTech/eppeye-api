package com.socialtech.eppeye.monitoring.application.services;

import com.socialtech.eppeye.monitoring.application.dto.request.MonitoringIncidentRequest;
import com.socialtech.eppeye.monitoring.application.dto.response.MonitoringIncidentResponse;
import com.socialtech.eppeye.shared.model.dto.response.ApiResponse;

public interface IMonitoringIncidentService {
    ApiResponse<MonitoringIncidentResponse> getMonitoringIncidentById(Long id);
    ApiResponse<MonitoringIncidentResponse> createMonitoringIncident(MonitoringIncidentRequest request);
    ApiResponse<MonitoringIncidentResponse> updateMonitoringIncident(Long id, MonitoringIncidentRequest request);
    ApiResponse<Void> deleteMonitoringIncident(Long id);
}

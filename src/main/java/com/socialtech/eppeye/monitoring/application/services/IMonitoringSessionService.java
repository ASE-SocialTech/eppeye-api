package com.socialtech.eppeye.monitoring.application.services;

import com.socialtech.eppeye.monitoring.application.dto.request.MonitoringSessionRequest;
import com.socialtech.eppeye.monitoring.application.dto.response.MonitoringSessionResponse;
import com.socialtech.eppeye.shared.model.dto.response.ApiResponse;

public interface IMonitoringSessionService {
    ApiResponse<MonitoringSessionResponse> getMonitoringSessionById(Long id);
    ApiResponse<MonitoringSessionResponse> createMonitoringSession(MonitoringSessionRequest request);
    ApiResponse<MonitoringSessionResponse> updateMonitoringSession(Long id, MonitoringSessionRequest request);
    ApiResponse<Void> deleteMonitoringSession(Long id);
}

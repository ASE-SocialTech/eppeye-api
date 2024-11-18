package com.socialtech.eppeye.monitoring.application.controller;

import com.socialtech.eppeye.monitoring.application.dto.request.MonitoringSessionRequest;
import com.socialtech.eppeye.monitoring.application.dto.response.MonitoringSessionResponse;
import com.socialtech.eppeye.monitoring.application.services.IMonitoringSessionService;
import com.socialtech.eppeye.shared.model.dto.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Monitoring Session")
@RestController
@RequestMapping("/api/v1/")
public class MonitoringSessionController {

    private final IMonitoringSessionService monitoringSessionService;

    public MonitoringSessionController(IMonitoringSessionService monitoringSessionService) {
        this.monitoringSessionService = monitoringSessionService;
    }

    @Operation(summary = "Get monitoring session by id")
    @GetMapping("/monitoring-sessions/{id}")
    public ResponseEntity<ApiResponse<MonitoringSessionResponse>> getMonitoringSessionById(@PathVariable Long id) {
        ApiResponse<MonitoringSessionResponse> res = monitoringSessionService.getMonitoringSessionById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Create monitoring session")
    @PostMapping("/monitoring-sessions")
    public ResponseEntity<ApiResponse<MonitoringSessionResponse>> createMonitoringSession(@RequestBody MonitoringSessionRequest request) {
        ApiResponse<MonitoringSessionResponse> res = monitoringSessionService.createMonitoringSession(request);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Update monitoring session")
    @PutMapping("/monitoring-sessions/{id}")
    public ResponseEntity<ApiResponse<MonitoringSessionResponse>> updateMonitoringSession(@PathVariable Long id, @RequestBody MonitoringSessionRequest request) {
        ApiResponse<MonitoringSessionResponse> res = monitoringSessionService.updateMonitoringSession(id, request);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Delete monitoring session")
    @DeleteMapping("/monitoring-sessions/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteMonitoringSession(@PathVariable Long id) {
        ApiResponse<Void> res = monitoringSessionService.deleteMonitoringSession(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}

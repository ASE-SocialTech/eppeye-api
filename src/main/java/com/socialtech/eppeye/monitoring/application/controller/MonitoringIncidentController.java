package com.socialtech.eppeye.monitoring.application.controller;

import com.socialtech.eppeye.monitoring.application.dto.request.MonitoringIncidentRequest;
import com.socialtech.eppeye.monitoring.application.dto.response.MonitoringIncidentResponse;
import com.socialtech.eppeye.monitoring.application.services.IMonitoringIncidentService;
import com.socialtech.eppeye.shared.model.dto.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Monitoring Incident")
@RestController
@RequestMapping("/api/v1/")
public class MonitoringIncidentController {

    private final IMonitoringIncidentService monitoringIncidentService;

    public MonitoringIncidentController(IMonitoringIncidentService monitoringIncidentService) {
        this.monitoringIncidentService = monitoringIncidentService;
    }

    @Operation(summary = "Get monitoring incident by id")
    @GetMapping("/monitoring-incidents/{id}")
    public ResponseEntity<ApiResponse<MonitoringIncidentResponse>> getMonitoringIncidentById(@PathVariable Long id) {
        ApiResponse<MonitoringIncidentResponse> res = monitoringIncidentService.getMonitoringIncidentById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Create monitoring incident")
    @PostMapping("/monitoring-incidents")
    public ResponseEntity<ApiResponse<MonitoringIncidentResponse>> createMonitoringIncident(@RequestBody MonitoringIncidentRequest request) {
        ApiResponse<MonitoringIncidentResponse> res = monitoringIncidentService.createMonitoringIncident(request);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Update monitoring incident")
    @PutMapping("/monitoring-incidents/{id}")
    public ResponseEntity<ApiResponse<MonitoringIncidentResponse>> updateMonitoringIncident(@PathVariable Long id, @RequestBody MonitoringIncidentRequest request) {
        ApiResponse<MonitoringIncidentResponse> res = monitoringIncidentService.updateMonitoringIncident(id, request);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Delete monitoring incident")
    @DeleteMapping("/monitoring-incidents/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteMonitoringIncident(@PathVariable Long id) {
        ApiResponse<Void> res = monitoringIncidentService.deleteMonitoringIncident(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}

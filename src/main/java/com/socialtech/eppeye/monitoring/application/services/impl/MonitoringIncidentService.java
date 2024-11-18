package com.socialtech.eppeye.monitoring.application.services.impl;

import com.socialtech.eppeye.monitoring.application.dto.request.MonitoringIncidentRequest;
import com.socialtech.eppeye.monitoring.application.dto.response.MonitoringIncidentResponse;
import com.socialtech.eppeye.monitoring.application.services.IMonitoringIncidentService;
import com.socialtech.eppeye.monitoring.domain.entities.MonitoringIncident;
import com.socialtech.eppeye.monitoring.domain.entities.MonitoringSession;
import com.socialtech.eppeye.monitoring.infrastructure.repositories.IMonitoringIncidentRepository;
import com.socialtech.eppeye.monitoring.infrastructure.repositories.IMonitoringSessionRepository;
import com.socialtech.eppeye.shared.model.dto.response.ApiResponse;
import com.socialtech.eppeye.shared.model.enums.Estatus;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MonitoringIncidentService implements IMonitoringIncidentService {

    private final IMonitoringIncidentRepository monitoringIncidentRepository;
    private final IMonitoringSessionRepository monitoringSessionRepository;
    private final ModelMapper modelMapper;

    public MonitoringIncidentService(IMonitoringIncidentRepository monitoringIncidentRepository,
                                     IMonitoringSessionRepository monitoringSessionRepository,
                                     ModelMapper modelMapper) {
        this.monitoringIncidentRepository = monitoringIncidentRepository;
        this.monitoringSessionRepository = monitoringSessionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ApiResponse<MonitoringIncidentResponse> getMonitoringIncidentById(Long id) {
        Optional<MonitoringIncident> monitoringIncidentOptional = monitoringIncidentRepository.findById(id);

        if (monitoringIncidentOptional.isEmpty()) {
            return new ApiResponse<>("Monitoring Incident not found", Estatus.ERROR, null);
        }

        MonitoringIncidentResponse response = modelMapper.map(monitoringIncidentOptional.get(), MonitoringIncidentResponse.class);
        return new ApiResponse<>("Monitoring Incident retrieved successfully", Estatus.SUCCESS, response);
    }

    @Override
    public ApiResponse<MonitoringIncidentResponse> createMonitoringIncident(MonitoringIncidentRequest request) {
        Optional<MonitoringSession> monitoringSessionOptional = monitoringSessionRepository.findById(request.getMonitoringSessionId());

        if (monitoringSessionOptional.isEmpty()) {
            return new ApiResponse<>("Monitoring Session not found", Estatus.ERROR, null);
        }

        MonitoringSession monitoringSession = monitoringSessionOptional.get();

        // Mapear y guardar directamente el incidente
        MonitoringIncident monitoringIncident = modelMapper.map(request, MonitoringIncident.class);
        monitoringIncident.setId(null);
        monitoringIncident.setMonitoringSession(monitoringSession);
        monitoringIncident = monitoringIncidentRepository.save(monitoringIncident); // Se genera el ID aquí

        // Asociar el incidente con la sesión (sin necesidad de re-guardar la sesión completa)
        monitoringSession.getIncidents().add(monitoringIncident);

        // Mapear la respuesta directamente desde el objeto guardado
        MonitoringIncidentResponse response = modelMapper.map(monitoringIncident, MonitoringIncidentResponse.class);

        return new ApiResponse<>("Monitoring Incident created successfully", Estatus.SUCCESS, response);
    }

    @Override
    public ApiResponse<MonitoringIncidentResponse> updateMonitoringIncident(Long id, MonitoringIncidentRequest request) {
        Optional<MonitoringIncident> monitoringIncidentOptional = monitoringIncidentRepository.findById(id);

        if (monitoringIncidentOptional.isEmpty()) {
            return new ApiResponse<>("Monitoring Incident not found", Estatus.ERROR, null);
        }

        MonitoringIncident monitoringIncident = monitoringIncidentOptional.get();

        if (request.getMonitoringSessionId() != null) {
            Optional<MonitoringSession> monitoringSessionOptional = monitoringSessionRepository.findById(request.getMonitoringSessionId());
            if (monitoringSessionOptional.isEmpty()) {
                return new ApiResponse<>("Monitoring Session not found", Estatus.ERROR, null);
            }
            monitoringIncident.setMonitoringSession(monitoringSessionOptional.get());
        }

        monitoringIncident.setIncidentType(request.getIncidentType());
        monitoringIncident.setDetectedTime(request.getDetectedTime());
        monitoringIncident.setDescription(request.getDescription());

        monitoringIncidentRepository.save(monitoringIncident);

        MonitoringIncidentResponse response = modelMapper.map(monitoringIncident, MonitoringIncidentResponse.class);
        return new ApiResponse<>("Monitoring Incident updated successfully", Estatus.SUCCESS, response);
    }

    @Override
    public ApiResponse<Void> deleteMonitoringIncident(Long id) {
        Optional<MonitoringIncident> monitoringIncidentOptional = monitoringIncidentRepository.findById(id);

        if (monitoringIncidentOptional.isEmpty()) {
            return new ApiResponse<>("Monitoring Incident not found", Estatus.ERROR, null);
        }

        monitoringIncidentRepository.deleteById(id);
        return new ApiResponse<>("Monitoring Incident deleted successfully", Estatus.SUCCESS, null);
    }
}

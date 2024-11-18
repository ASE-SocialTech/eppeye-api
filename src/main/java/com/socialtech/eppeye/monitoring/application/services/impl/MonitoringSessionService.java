package com.socialtech.eppeye.monitoring.application.services.impl;

import com.socialtech.eppeye.monitoring.application.dto.request.MonitoringIncidentRequest;
import com.socialtech.eppeye.monitoring.application.dto.request.MonitoringSessionRequest;
import com.socialtech.eppeye.monitoring.application.dto.response.ComplianceStatusResponse;
import com.socialtech.eppeye.monitoring.application.dto.response.MonitoringSessionResponse;
import com.socialtech.eppeye.monitoring.application.services.IMonitoringSessionService;
import com.socialtech.eppeye.monitoring.domain.entities.Camera;
import com.socialtech.eppeye.monitoring.domain.entities.ComplianceStatus;
import com.socialtech.eppeye.monitoring.domain.entities.MonitoringIncident;
import com.socialtech.eppeye.monitoring.domain.entities.MonitoringSession;
import com.socialtech.eppeye.monitoring.infrastructure.repositories.ICameraRepository;
import com.socialtech.eppeye.monitoring.infrastructure.repositories.IComplianceStatusRepository;
import com.socialtech.eppeye.monitoring.infrastructure.repositories.IMonitoringIncidentRepository;
import com.socialtech.eppeye.monitoring.infrastructure.repositories.IMonitoringSessionRepository;
import com.socialtech.eppeye.shared.model.dto.response.ApiResponse;
import com.socialtech.eppeye.shared.model.enums.Estatus;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class MonitoringSessionService implements IMonitoringSessionService {

    private final IMonitoringSessionRepository monitoringSessionRepository;
    private final ICameraRepository cameraRepository;
    private final IComplianceStatusRepository complianceStatusRepository;
    private final IMonitoringIncidentRepository monitoringIncidentRepository;
    private final ModelMapper modelMapper;

    public MonitoringSessionService(
            IMonitoringSessionRepository monitoringSessionRepository,
            ICameraRepository cameraRepository,
            IComplianceStatusRepository complianceStatusRepository,
            IMonitoringIncidentRepository monitoringIncidentRepository,
            ModelMapper modelMapper) {
        this.monitoringSessionRepository = monitoringSessionRepository;
        this.cameraRepository = cameraRepository;
        this.complianceStatusRepository = complianceStatusRepository;
        this.monitoringIncidentRepository = monitoringIncidentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ApiResponse<MonitoringSessionResponse> getMonitoringSessionById(Long id) {
        Optional<MonitoringSession> sessionOptional = monitoringSessionRepository.findById(id);
        if (sessionOptional.isEmpty()) {
            return new ApiResponse<>("Monitoring session not found", Estatus.ERROR, null);
        }

        MonitoringSession session = sessionOptional.get();
        MonitoringSessionResponse response = modelMapper.map(session, MonitoringSessionResponse.class);

        if (session.getComplianceStatus() != null) {
            ComplianceStatus complianceStatus = session.getComplianceStatus();
            response.setComplianceStatus(modelMapper.map(complianceStatus, ComplianceStatusResponse.class));
        }

        return new ApiResponse<>("Monitoring session retrieved successfully", Estatus.SUCCESS, response);
    }

    @Override
    public ApiResponse<MonitoringSessionResponse> createMonitoringSession(MonitoringSessionRequest request) {

        Optional<Camera> cameraOptional = cameraRepository.findById(request.getCameraId());
        if (cameraOptional.isEmpty()) {
            return new ApiResponse<>("Camera not found", Estatus.ERROR, null);
        }
        Camera camera = cameraOptional.get();

        // Crear ComplianceStatus inicialmente sin asignar el ID de la sesión
        ComplianceStatus complianceStatus = new ComplianceStatus();
        complianceStatus.setCompliant(true);
        complianceStatus.setDescription("Compliant");
        complianceStatusRepository.save(complianceStatus);  // Guardar inicialmente sin el ID de la sesión

        // Crear MonitoringSession
        MonitoringSession session = new MonitoringSession();
        session.setStartTime(request.getStartTime());
        session.setEndTime(request.getEndTime());
        session.setStatus(request.getStatus());
        session.setCamera(camera);
        session.setComplianceStatus(complianceStatus);  // Asegurarse de que ComplianceStatus esté asociado a la sesión
        session.setIncidents(new ArrayList<MonitoringIncident>());

        // Guardar la sesión de monitoreo
        monitoringSessionRepository.save(session);

        // Ahora que la sesión está guardada, actualizar ComplianceStatus con el ID de la sesión
        complianceStatus.setMonitoringSession(session);  // Vincular la sesión de monitoreo a ComplianceStatus
        complianceStatusRepository.save(complianceStatus);  // Guardar ComplianceStatus con el ID de la sesión

        // Mapear la respuesta
        MonitoringSessionResponse response = modelMapper.map(session, MonitoringSessionResponse.class);
        if (session.getComplianceStatus() != null) {
            response.setComplianceStatus(modelMapper.map(session.getComplianceStatus(), ComplianceStatusResponse.class));
        }

        return new ApiResponse<>("Monitoring Session created successfully", Estatus.SUCCESS, response);
    }


    @Override
    public ApiResponse<MonitoringSessionResponse> updateMonitoringSession(Long id, MonitoringSessionRequest request) {

        Optional<MonitoringSession> sessionOptional = monitoringSessionRepository.findById(id);
        if (sessionOptional.isEmpty()) {
            return new ApiResponse<>("Monitoring session not found", Estatus.ERROR, null);
        }
        MonitoringSession session = sessionOptional.get();

        Optional<Camera> cameraOptional = cameraRepository.findById(request.getCameraId());
        if (cameraOptional.isEmpty()) {
            return new ApiResponse<>("Camera not found", Estatus.ERROR, null);
        }
        Camera camera = cameraOptional.get();

        Optional<ComplianceStatus> complianceStatusOptional = complianceStatusRepository.findById(session.getComplianceStatus().getId());
        if (complianceStatusOptional.isEmpty()) {
            return new ApiResponse<>("Compliance Status not found", Estatus.ERROR, null);
        }
        ComplianceStatus complianceStatus = complianceStatusOptional.get();

        session.setStartTime(request.getStartTime());
        session.setEndTime(request.getEndTime());
        session.setStatus(request.getStatus());
        session.setCamera(camera);
        session.setComplianceStatus(complianceStatus);

        var incidents = new ArrayList<MonitoringIncident>();
        session.setIncidents(incidents);

        monitoringSessionRepository.save(session);

        MonitoringSessionResponse response = modelMapper.map(session, MonitoringSessionResponse.class);
        if (session.getComplianceStatus() != null) {
            response.setComplianceStatus(modelMapper.map(session.getComplianceStatus(), ComplianceStatusResponse.class));
        }

        return new ApiResponse<>("Monitoring Session updated successfully", Estatus.SUCCESS, response);
    }

    @Override
    public ApiResponse<Void> deleteMonitoringSession(Long id) {
        Optional<MonitoringSession> sessionOptional = monitoringSessionRepository.findById(id);
        if (sessionOptional.isEmpty()) {
            return new ApiResponse<>("Monitoring session not found", Estatus.ERROR, null);
        }
        MonitoringSession session = sessionOptional.get();

        monitoringIncidentRepository.deleteAll(session.getIncidents());
        monitoringSessionRepository.delete(session);

        return new ApiResponse<>("Monitoring Session deleted successfully", Estatus.SUCCESS, null);
    }
}

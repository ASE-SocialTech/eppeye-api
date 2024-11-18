package com.socialtech.eppeye.monitoring.application.services.impl;

import com.socialtech.eppeye.monitoring.application.dto.request.ComplianceStatusRequest;
import com.socialtech.eppeye.monitoring.application.dto.response.ComplianceStatusResponse;
import com.socialtech.eppeye.monitoring.application.services.IComplianceStatusService;
import com.socialtech.eppeye.monitoring.domain.entities.ComplianceStatus;
import com.socialtech.eppeye.monitoring.domain.entities.MonitoringSession;
import com.socialtech.eppeye.monitoring.infrastructure.repositories.IComplianceStatusRepository;
import com.socialtech.eppeye.monitoring.infrastructure.repositories.IMonitoringSessionRepository;
import com.socialtech.eppeye.shared.model.dto.response.ApiResponse;
import com.socialtech.eppeye.shared.model.enums.Estatus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ComplianceStatusService implements IComplianceStatusService {

    private final IComplianceStatusRepository complianceStatusRepository;
    private final IMonitoringSessionRepository monitoringSessionRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ComplianceStatusService(IComplianceStatusRepository complianceStatusRepository,
                                   IMonitoringSessionRepository monitoringSessionRepository,
                                   ModelMapper modelMapper) {
        this.complianceStatusRepository = complianceStatusRepository;
        this.monitoringSessionRepository = monitoringSessionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ApiResponse<ComplianceStatusResponse> getComplianceStatusById(Long id) {
        Optional<ComplianceStatus> complianceStatusOptional = complianceStatusRepository.findById(id);
        if (complianceStatusOptional.isEmpty()) {
            return new ApiResponse<>("Compliance Status not found", Estatus.ERROR, null);
        }

        ComplianceStatusResponse complianceStatusResponse = modelMapper.map(complianceStatusOptional.get(), ComplianceStatusResponse.class);
        return new ApiResponse<>("Compliance Status retrieved successfully", Estatus.SUCCESS, complianceStatusResponse);
    }

    @Override
    public ApiResponse<ComplianceStatusResponse> createComplianceStatus(ComplianceStatusRequest request) {
        Optional<MonitoringSession> monitoringSessionOptional = monitoringSessionRepository.findById(request.getMonitoringSessionId());
        if (monitoringSessionOptional.isEmpty()) {
            return new ApiResponse<>("Monitoring Session not found", Estatus.ERROR, null);
        }

        ComplianceStatus complianceStatus = modelMapper.map(request, ComplianceStatus.class);
        complianceStatus.setMonitoringSession(monitoringSessionOptional.get());

        complianceStatusRepository.save(complianceStatus);

        ComplianceStatusResponse complianceStatusResponse = modelMapper.map(complianceStatus, ComplianceStatusResponse.class);
        return new ApiResponse<>("Compliance Status created successfully", Estatus.SUCCESS, complianceStatusResponse);
    }

    @Override
    public ApiResponse<ComplianceStatusResponse> updateComplianceStatus(Long id, ComplianceStatusRequest request) {
        Optional<ComplianceStatus> complianceStatusOptional = complianceStatusRepository.findById(id);
        if (complianceStatusOptional.isEmpty()) {
            return new ApiResponse<>("Compliance Status not found", Estatus.ERROR, null);
        }

        ComplianceStatus complianceStatus = complianceStatusOptional.get();
        complianceStatus.setCompliant(request.isCompliant());
        complianceStatus.setDescription(request.getDescription());

        complianceStatusRepository.save(complianceStatus);

        ComplianceStatusResponse complianceStatusResponse = modelMapper.map(complianceStatus, ComplianceStatusResponse.class);
        return new ApiResponse<>("Compliance Status updated successfully", Estatus.SUCCESS, complianceStatusResponse);
    }

    @Override
    public ApiResponse<Void> deleteComplianceStatus(Long id) {
        Optional<ComplianceStatus> complianceStatusOptional = complianceStatusRepository.findById(id);
        if (complianceStatusOptional.isEmpty()) {
            return new ApiResponse<>("Compliance Status not found", Estatus.ERROR, null);
        }

        complianceStatusRepository.deleteById(id);
        return new ApiResponse<>("Compliance Status deleted successfully", Estatus.SUCCESS, null);
    }
}

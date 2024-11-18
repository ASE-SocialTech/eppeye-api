package com.socialtech.eppeye.monitoring.application.services;

import com.socialtech.eppeye.monitoring.application.dto.request.ComplianceStatusRequest;
import com.socialtech.eppeye.monitoring.application.dto.response.ComplianceStatusResponse;
import com.socialtech.eppeye.shared.model.dto.response.ApiResponse;

public interface IComplianceStatusService {

    ApiResponse<ComplianceStatusResponse> getComplianceStatusById(Long id);
    ApiResponse<ComplianceStatusResponse> createComplianceStatus(ComplianceStatusRequest request);
    ApiResponse<ComplianceStatusResponse> updateComplianceStatus(Long id, ComplianceStatusRequest request);
    ApiResponse<Void> deleteComplianceStatus(Long id);
}

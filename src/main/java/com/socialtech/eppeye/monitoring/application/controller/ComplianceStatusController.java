package com.socialtech.eppeye.monitoring.application.controller;

import com.socialtech.eppeye.monitoring.application.dto.request.ComplianceStatusRequest;
import com.socialtech.eppeye.monitoring.application.dto.response.ComplianceStatusResponse;
import com.socialtech.eppeye.monitoring.application.services.IComplianceStatusService;
import com.socialtech.eppeye.shared.model.dto.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Compliance Status")
@RestController
@RequestMapping("/api/v1/")
public class ComplianceStatusController {

    private final IComplianceStatusService complianceStatusService;

    public ComplianceStatusController(IComplianceStatusService complianceStatusService) {
        this.complianceStatusService = complianceStatusService;
    }

    @Operation(summary = "Get compliance status by id")
    @GetMapping("/compliance-status/{id}")
    public ResponseEntity<ApiResponse<ComplianceStatusResponse>> getComplianceStatusById(@PathVariable Long id) {
        ApiResponse<ComplianceStatusResponse> res = complianceStatusService.getComplianceStatusById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Create compliance status")
    @PostMapping("/compliance-status")
    public ResponseEntity<ApiResponse<ComplianceStatusResponse>> createComplianceStatus(@RequestBody ComplianceStatusRequest request) {
        ApiResponse<ComplianceStatusResponse> res = complianceStatusService.createComplianceStatus(request);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Update compliance status")
    @PutMapping("/compliance-status/{id}")
    public ResponseEntity<ApiResponse<ComplianceStatusResponse>> updateComplianceStatus(@PathVariable Long id, @RequestBody ComplianceStatusRequest request) {
        ApiResponse<ComplianceStatusResponse> res = complianceStatusService.updateComplianceStatus(id, request);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Delete compliance status")
    @DeleteMapping("/compliance-status/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteComplianceStatus(@PathVariable Long id) {
        ApiResponse<Void> res = complianceStatusService.deleteComplianceStatus(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}

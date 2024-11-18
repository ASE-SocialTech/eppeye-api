package com.socialtech.eppeye.monitoring.application.controller;

import com.socialtech.eppeye.monitoring.application.dto.request.AreaRequest;
import com.socialtech.eppeye.monitoring.application.dto.response.AreaResponse;
import com.socialtech.eppeye.monitoring.application.services.IAreaService;
import com.socialtech.eppeye.shared.model.dto.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Areas")
@RestController
@RequestMapping("/api/v1/")
public class AreaController {

    private final IAreaService areaService;

    public AreaController(IAreaService areaService) {
        this.areaService = areaService;
    }

    @Operation(summary = "Get area by id")
    @GetMapping("/areas/{id}")
    public ResponseEntity<ApiResponse<AreaResponse>> getAreaById(@PathVariable Long id) {
        ApiResponse<AreaResponse> res = areaService.getAreaById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Create area")
    @PostMapping("/areas")
    public ResponseEntity<ApiResponse<AreaResponse>> createArea(@RequestBody AreaRequest request) {
        ApiResponse<AreaResponse> res = areaService.createArea(request);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Update area")
    @PutMapping("/areas/{id}")
    public ResponseEntity<ApiResponse<AreaResponse>> updateArea(@PathVariable Long id, @RequestBody AreaRequest request) {
        ApiResponse<AreaResponse> res = areaService.updateArea(id, request);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Delete area")
    @DeleteMapping("/areas/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteArea(@PathVariable Long id) {
        ApiResponse<Void> res = areaService.deleteArea(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}

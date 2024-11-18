package com.socialtech.eppeye.monitoring.application.controller;

import com.socialtech.eppeye.monitoring.application.dto.request.CameraRequest;
import com.socialtech.eppeye.monitoring.application.dto.response.CameraResponse;
import com.socialtech.eppeye.monitoring.application.services.ICameraService;
import com.socialtech.eppeye.shared.model.dto.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Cameras")
@RestController
@RequestMapping("/api/v1/")
public class CameraController {

    private final ICameraService cameraService;

    public CameraController(ICameraService cameraService) {
        this.cameraService = cameraService;
    }

    @Operation(summary = "Get camera by id")
    @GetMapping("/cameras/{id}")
    public ResponseEntity<ApiResponse<CameraResponse>> getCameraById(@PathVariable Long id) {
        ApiResponse<CameraResponse> res = cameraService.getCameraById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Create camera")
    @PostMapping("/cameras")
    public ResponseEntity<ApiResponse<CameraResponse>> createCamera(@RequestBody CameraRequest request) {
        ApiResponse<CameraResponse> res = cameraService.createCamera(request);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Update camera")
    @PutMapping("/cameras/{id}")
    public ResponseEntity<ApiResponse<CameraResponse>> updateCamera(@PathVariable Long id, @RequestBody CameraRequest request) {
        ApiResponse<CameraResponse> res = cameraService.updateCamera(id, request);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Delete camera")
    @DeleteMapping("/cameras/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCamera(@PathVariable Long id) {
        ApiResponse<Void> res = cameraService.deleteCamera(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}

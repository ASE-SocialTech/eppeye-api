package com.socialtech.eppeye.monitoring.application.services;

import com.socialtech.eppeye.monitoring.application.dto.request.CameraRequest;
import com.socialtech.eppeye.monitoring.application.dto.response.CameraResponse;
import com.socialtech.eppeye.shared.model.dto.response.ApiResponse;

public interface ICameraService {
    ApiResponse<CameraResponse> getCameraById(Long id);
    ApiResponse<CameraResponse> createCamera(CameraRequest request);
    ApiResponse<CameraResponse> updateCamera(Long id, CameraRequest request);
    ApiResponse<Void> deleteCamera(Long id);
}

package com.socialtech.eppeye.monitoring.application.services;

import com.socialtech.eppeye.monitoring.application.dto.request.AreaRequest;
import com.socialtech.eppeye.monitoring.application.dto.response.AreaResponse;
import com.socialtech.eppeye.shared.model.dto.response.ApiResponse;

public interface IAreaService {
    ApiResponse<AreaResponse> getAreaById(Long id);
    ApiResponse<AreaResponse> createArea(AreaRequest request);
    ApiResponse<AreaResponse> updateArea(Long id, AreaRequest request);
    ApiResponse<Void> deleteArea(Long id);
}

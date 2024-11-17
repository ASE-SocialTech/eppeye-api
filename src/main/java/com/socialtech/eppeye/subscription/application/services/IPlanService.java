package com.socialtech.eppeye.subscription.application.services;

import com.socialtech.eppeye.shared.model.dto.response.ApiResponse;
import com.socialtech.eppeye.subscription.application.dto.request.PlanRequest;
import com.socialtech.eppeye.subscription.application.dto.response.PlanResponse;

public interface IPlanService {
    ApiResponse<PlanResponse> getPlanById(Long id);
    ApiResponse<PlanResponse> createPlan(PlanRequest request);
    ApiResponse<PlanResponse> updatePlan(Long id, PlanRequest request);
    ApiResponse<Void> deletePlan(Long id);

}

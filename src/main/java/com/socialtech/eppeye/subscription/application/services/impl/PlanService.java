package com.socialtech.eppeye.subscription.application.services.impl;

import com.socialtech.eppeye.shared.model.dto.response.ApiResponse;
import com.socialtech.eppeye.shared.model.enums.Estatus;
import com.socialtech.eppeye.subscription.application.dto.request.PlanRequest;
import com.socialtech.eppeye.subscription.application.dto.response.PlanResponse;
import com.socialtech.eppeye.subscription.application.services.IPlanService;
import com.socialtech.eppeye.subscription.domain.entities.Plan;
import com.socialtech.eppeye.subscription.infrastructure.repositories.IPlanRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlanService implements IPlanService {
    private final IPlanRepository planRepository;
    private final ModelMapper modelMapper;

    private PlanService (IPlanRepository planRepository, ModelMapper modelMapper){
        this.planRepository = planRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ApiResponse<PlanResponse> getPlanById(Long id) {
        Optional<Plan> planOptional = planRepository.findById(id);
        if (planOptional.isEmpty()){
            return new ApiResponse<>("Plan not found", Estatus.ERROR, null);
        } else {
            Plan plan = planOptional.get();
            var planResponseDto = modelMapper.map(plan, PlanResponse.class);
            return new ApiResponse<>("Plan found successfully", Estatus.SUCCESS, planResponseDto);
        }
    }

    @Override
    public ApiResponse<PlanResponse> createPlan(PlanRequest request) {
        var plan = modelMapper.map(request, Plan.class);
        planRepository.save(plan);
        var planResponseDto = modelMapper.map(plan, PlanResponse.class);
        return new ApiResponse<>("Plan created successfully", Estatus.SUCCESS, planResponseDto);
    }

    @Override
    public ApiResponse<PlanResponse> updatePlan(Long id, PlanRequest request) {
        Optional<Plan> planOptional = planRepository.findById(id);

        if (planOptional.isEmpty()){
            return new ApiResponse<>("Plan not found", Estatus.ERROR, null);
        } else {
            Plan plan = planOptional.get();
            modelMapper.map(request, plan);
            planRepository.save(plan);
            PlanResponse planResponse = modelMapper.map(plan, PlanResponse.class);
            return new ApiResponse<>("Plan updated successfully", Estatus.SUCCESS, planResponse);
        }
    }

    @Override
    public ApiResponse<Void> deletePlan(Long id) {
        Optional<Plan> planOptional = planRepository.findById(id);

        if (planOptional.isEmpty()){
            return new ApiResponse<>("Plan not found", Estatus.ERROR, null);
        } else {
            planRepository.deleteById(id);
            return new ApiResponse<>("Plan deleted successfully", Estatus.SUCCESS, null);
        }
    }
}

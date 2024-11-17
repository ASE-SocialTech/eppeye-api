package com.socialtech.eppeye.subscription.application.controller;

import com.socialtech.eppeye.shared.model.dto.response.ApiResponse;
import com.socialtech.eppeye.subscription.application.dto.request.PlanRequest;
import com.socialtech.eppeye.subscription.application.dto.response.PlanResponse;
import com.socialtech.eppeye.subscription.application.services.impl.PlanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SecurityRequirements
@Tag(name = "Plans")
@RestController
@RequestMapping("/api/v1/")
public class PlanController {

    private final PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @Operation(summary = "Get plan by id")
    @GetMapping("/plans/{id}")
    public ResponseEntity<ApiResponse<PlanResponse>> getPlanById(@PathVariable Long id) {
        var res = planService.getPlanById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }


    @Operation(summary = "Create plan")
    @PostMapping("/plans")
    public ResponseEntity<ApiResponse<PlanResponse>> createPlan(@RequestBody PlanRequest planRequestDto) {
        var res = planService.createPlan(planRequestDto);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Update plan")
    @PutMapping("/plans/{id}")
    public ResponseEntity<ApiResponse<PlanResponse>> updatePlan(@PathVariable Long id, @RequestBody PlanRequest planRequestDto) {
        var res = planService.updatePlan(id, planRequestDto);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Delete plan")
    @DeleteMapping("/plans/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePlan(@PathVariable Long id) {
        var res = planService.deletePlan(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}

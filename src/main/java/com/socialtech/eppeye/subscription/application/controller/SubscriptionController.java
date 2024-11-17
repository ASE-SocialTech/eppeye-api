package com.socialtech.eppeye.subscription.application.controller;

import com.socialtech.eppeye.shared.model.dto.response.ApiResponse;
import com.socialtech.eppeye.subscription.application.dto.request.PlanRequest;
import com.socialtech.eppeye.subscription.application.dto.request.SubscriptionRequest;
import com.socialtech.eppeye.subscription.application.dto.response.PlanResponse;
import com.socialtech.eppeye.subscription.application.dto.response.SubscriptionResponse;
import com.socialtech.eppeye.subscription.application.services.impl.SubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SecurityRequirements
@Tag(name = "Subscriptions")
@RestController
@RequestMapping("/api/v1/")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @Operation(summary = "Get subscription by id")
    @GetMapping("/subscriptions/{id}")
    public ResponseEntity<ApiResponse<SubscriptionResponse>> getSubscriptionById(@PathVariable Long id) {
        var res = subscriptionService.getSubscriptionById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Get subscription by user id")
    @GetMapping("/subscriptions/user/{id}")
    public ResponseEntity<ApiResponse<SubscriptionResponse>> getSubscriptionByUserId(@PathVariable Long id) {
        var res =subscriptionService.getSubscriptionByUserId(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Create subscription")
    @PostMapping("/subscriptions")
    public ResponseEntity<ApiResponse<SubscriptionResponse>> createSubscription(@RequestBody SubscriptionRequest subscriptionRequestDto) {
        var res = subscriptionService.createSubscription(subscriptionRequestDto);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Update subscription")
    @PutMapping("/subscriptions/{id}")
    public ResponseEntity<ApiResponse<SubscriptionResponse>> updateSubscription(@PathVariable Long id, @RequestBody SubscriptionRequest subscriptionRequestDto) {
        var res = subscriptionService.updateSubscription(id, subscriptionRequestDto);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Delete subscription")
    @DeleteMapping("/subscriptions/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteSubscription(@PathVariable Long id) {
        var res = subscriptionService.deleteSubscription(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}

package com.socialtech.eppeye.subscription.application.services;

import com.socialtech.eppeye.shared.model.dto.response.ApiResponse;
import com.socialtech.eppeye.subscription.application.dto.request.SubscriptionRequest;
import com.socialtech.eppeye.subscription.application.dto.response.SubscriptionResponse;

public interface ISubscriptionService {
    ApiResponse<SubscriptionResponse> getSubscriptionById(Long id);
    ApiResponse<SubscriptionResponse> createSubscription(SubscriptionRequest request);
    ApiResponse<SubscriptionResponse> updateSubscription(Long id, SubscriptionRequest request);
    ApiResponse<Void> deleteSubscription(Long id);
    ApiResponse<SubscriptionResponse> getSubscriptionByUserId(Long userId);
}

package com.socialtech.eppeye.subscription.application.services.impl;

import com.socialtech.eppeye.account.domain.entities.User;
import com.socialtech.eppeye.account.infrastructure.repositories.IUserRepository;
import com.socialtech.eppeye.shared.model.dto.response.ApiResponse;
import com.socialtech.eppeye.shared.model.enums.Estatus;
import com.socialtech.eppeye.subscription.application.dto.request.SubscriptionRequest;
import com.socialtech.eppeye.subscription.application.dto.response.SubscriptionResponse;
import com.socialtech.eppeye.subscription.application.services.ISubscriptionService;
import com.socialtech.eppeye.subscription.domain.entities.Plan;
import com.socialtech.eppeye.subscription.domain.entities.Subscription;
import com.socialtech.eppeye.subscription.infrastructure.repositories.IPlanRepository;
import com.socialtech.eppeye.subscription.infrastructure.repositories.ISubscriptionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubscriptionService implements ISubscriptionService {
    private final ISubscriptionRepository subscriptionRepository;
    private final IUserRepository userRepository;
    private final IPlanRepository planRepository;
    private final ModelMapper modelMapper;

    private SubscriptionService (ISubscriptionRepository subscriptionRepository,IUserRepository userRepository, IPlanRepository planRepository, ModelMapper modelMapper){
        this.subscriptionRepository = subscriptionRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.planRepository = planRepository;
    }

    @Override
    public ApiResponse<SubscriptionResponse> getSubscriptionById(Long id) {
        Optional<Subscription> subscriptionOptional = subscriptionRepository.findById(id);
        if (subscriptionOptional.isEmpty()){
            return new ApiResponse<>("Subscription not found", Estatus.ERROR, null);
        } else {
            Subscription subscription = subscriptionOptional.get();
            var subscriptionResponseDto = modelMapper.map(subscription, SubscriptionResponse.class);
            return new ApiResponse<>("Subscription found successfully", Estatus.SUCCESS, subscriptionResponseDto);
        }
    }

    @Override
    public ApiResponse<SubscriptionResponse> createSubscription(SubscriptionRequest request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        Plan plan = planRepository.findById(request.getPlanId()).orElseThrow(() -> new RuntimeException("Plan not found"));

        if (request.getStartDate().isAfter(request.getEndDate())) {
            return new ApiResponse<>("Start date cannot be after end date", Estatus.ERROR, null);
        }

        Subscription subscription = Subscription.builder()
                .user(user)
                .plan(plan)
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .status(request.getStatus())
                .build();

        subscriptionRepository.save(subscription);

        SubscriptionResponse subscriptionResponseDto = new SubscriptionResponse(subscription.getId(), subscription.getUser().getId(), subscription.getPlan().getId(), subscription.getStartDate(), subscription.getEndDate(), subscription.getStatus());

        return new ApiResponse<>("Subscription created successfully", Estatus.SUCCESS, subscriptionResponseDto);

    }

    @Override
    public ApiResponse<SubscriptionResponse> updateSubscription(Long id, SubscriptionRequest request) {
        return null;
    }

    @Override
    public ApiResponse<Void> deleteSubscription(Long id) {
        return null;
    }

    @Override
    public ApiResponse<SubscriptionResponse> getSubscriptionByUserId(Long userId) {
        Optional<Subscription> subscriptionOptional = subscriptionRepository.findByUserId(userId);
        if (subscriptionOptional.isEmpty()) {
            return new ApiResponse<>("Subscription not found for the user", Estatus.ERROR, null);
        } else {
            Subscription subscription = subscriptionOptional.get();
            SubscriptionResponse subscriptionResponseDto = modelMapper.map(subscription, SubscriptionResponse.class);
            return new ApiResponse<>("Subscription found successfully", Estatus.SUCCESS, subscriptionResponseDto);
        }
    }
}

package com.socialtech.eppeye.subscription.application.services.impl;

import com.socialtech.eppeye.shared.model.dto.response.ApiResponse;
import com.socialtech.eppeye.shared.model.enums.Estatus;
import com.socialtech.eppeye.subscription.application.dto.request.PaymentDetailsRequest;
import com.socialtech.eppeye.subscription.application.dto.response.PaymentDetailsResponse;
import com.socialtech.eppeye.subscription.application.dto.response.SubscriptionResponse;
import com.socialtech.eppeye.subscription.application.services.IPaymentDetailsService;
import com.socialtech.eppeye.subscription.domain.entities.PaymentDetails;
import com.socialtech.eppeye.subscription.domain.entities.Subscription;
import com.socialtech.eppeye.subscription.infrastructure.repositories.IPaymentDetailsRepository;
import com.socialtech.eppeye.subscription.infrastructure.repositories.ISubscriptionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentDetailsService implements IPaymentDetailsService {

    private final IPaymentDetailsRepository paymentDetailsRepository;
    private final ISubscriptionRepository subscriptionRepository;
    private final ModelMapper modelMapper;

    private PaymentDetailsService (
            IPaymentDetailsRepository paymentDetailsRepository,
            ISubscriptionRepository subscriptionRepository,
            ModelMapper modelMapper){
        this.paymentDetailsRepository = paymentDetailsRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public ApiResponse<PaymentDetailsResponse> getPaymentDetailsById(Long id) {
        Optional<PaymentDetails> paymentDetailsOptional = paymentDetailsRepository.findById(id);
        if (paymentDetailsOptional.isEmpty()){
            return new ApiResponse<>("Payment details not found", Estatus.ERROR, null);
        } else {
            PaymentDetails paymentDetails = paymentDetailsOptional.get();
            var paymentDetailsResponseDto = modelMapper.map(paymentDetails, PaymentDetailsResponse.class);
            return new ApiResponse<>("Payment details found successfully", Estatus.SUCCESS,paymentDetailsResponseDto);
        }
    }

    public ApiResponse<PaymentDetailsResponse> getPaymentDetailsBySubscriptionId(Long id) {
        Optional<PaymentDetails> paymentDetailsOptional = paymentDetailsRepository.findBySubscriptionId(id);
        if (paymentDetailsOptional.isEmpty()){
            return new ApiResponse<>("Payment details not found", Estatus.ERROR, null);
        } else {
            PaymentDetails paymentDetails = paymentDetailsOptional.get();
            var paymentDetailsResponseDto = modelMapper.map(paymentDetails, PaymentDetailsResponse.class);
            return new ApiResponse<>("Payment details found successfully", Estatus.SUCCESS,paymentDetailsResponseDto);
        }
    }

    @Override
    public ApiResponse<PaymentDetailsResponse> createPaymentDetails(PaymentDetailsRequest request) {
        if (request.getSubscriptionId() == null) {
            return new ApiResponse<>("Subscription ID must be provided", Estatus.ERROR, null);
        }

        Optional<Subscription> subscriptionOptional = subscriptionRepository.findById(request.getSubscriptionId());
        if (subscriptionOptional.isEmpty()) {
            return new ApiResponse<>("Subscription not found", Estatus.ERROR, null);
        }

        PaymentDetails paymentDetails = modelMapper.map(request, PaymentDetails.class);
        paymentDetails.setSubscription(subscriptionOptional.get());

        PaymentDetails savedPaymentDetails = paymentDetailsRepository.save(paymentDetails);

        PaymentDetailsResponse paymentDetailsResponseDto = modelMapper.map(savedPaymentDetails, PaymentDetailsResponse.class);
        SubscriptionResponse subscriptionResponse = modelMapper.map(savedPaymentDetails.getSubscription(), SubscriptionResponse.class);
        paymentDetailsResponseDto.setSubscription(subscriptionResponse);

        return new ApiResponse<>("Payment details created successfully", Estatus.SUCCESS, paymentDetailsResponseDto);
    }

    @Override
    public ApiResponse<PaymentDetailsResponse> updatePaymentDetails(Long id, PaymentDetailsRequest request) {
        Optional<PaymentDetails> paymentDetailsOptional = paymentDetailsRepository.findById(id);
        if(paymentDetailsOptional.isEmpty()){
            return new ApiResponse<>("Payment details not found", Estatus.ERROR, null);
        } else {
            PaymentDetails paymentDetails = paymentDetailsOptional.get();
            modelMapper.map(request, paymentDetails);
            paymentDetailsRepository.save(paymentDetails);
            PaymentDetailsResponse paymentDetailsResponse = modelMapper.map(paymentDetails, PaymentDetailsResponse.class);
            return new ApiResponse<>("Payment details updated successfully", Estatus.SUCCESS, paymentDetailsResponse);
        }
    }

    @Override
    public ApiResponse<Void> deletePaymentDetails(Long id) {
        Optional<PaymentDetails> paymentDetailsOptional = paymentDetailsRepository.findById(id);
        if(paymentDetailsOptional.isEmpty()){
            return new ApiResponse<>("Payment details not found", Estatus.ERROR, null);
        } else {
            paymentDetailsRepository.deleteById(id);
            return new ApiResponse<>("Payment details deleted successfully", Estatus.SUCCESS, null);
        }
    }
}

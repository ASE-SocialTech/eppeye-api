package com.socialtech.eppeye.subscription.application.services;

import com.socialtech.eppeye.shared.model.dto.response.ApiResponse;
import com.socialtech.eppeye.subscription.application.dto.request.PaymentDetailsRequest;
import com.socialtech.eppeye.subscription.application.dto.response.PaymentDetailsResponse;
import org.springframework.stereotype.Service;

public interface IPaymentDetailsService  {
    ApiResponse<PaymentDetailsResponse> getPaymentRequestById(Long id);
    ApiResponse<PaymentDetailsResponse> getPaymentRequestBySubscriptionId(Long id);
    ApiResponse<PaymentDetailsResponse> createPaymentRequest(PaymentDetailsRequest request);
    ApiResponse<PaymentDetailsResponse> updatePaymentRequest(Long id, PaymentDetailsRequest request);
    ApiResponse<Void> deletePaymentRequest(Long id);
}

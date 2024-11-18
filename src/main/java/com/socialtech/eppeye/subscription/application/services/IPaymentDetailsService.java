package com.socialtech.eppeye.subscription.application.services;

import com.socialtech.eppeye.shared.model.dto.response.ApiResponse;
import com.socialtech.eppeye.subscription.application.dto.request.PaymentDetailsRequest;
import com.socialtech.eppeye.subscription.application.dto.response.PaymentDetailsResponse;
import org.springframework.stereotype.Service;

public interface IPaymentDetailsService  {
    ApiResponse<PaymentDetailsResponse> getPaymentDetailsById(Long id);
    ApiResponse<PaymentDetailsResponse> getPaymentDetailsBySubscriptionId(Long id);
    ApiResponse<PaymentDetailsResponse> createPaymentDetails(PaymentDetailsRequest request);
    ApiResponse<PaymentDetailsResponse> updatePaymentDetails(Long id, PaymentDetailsRequest request);
    ApiResponse<Void> deletePaymentDetails(Long id);
}

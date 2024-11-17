package com.socialtech.eppeye.subscription.application.controller;

import com.socialtech.eppeye.shared.model.dto.response.ApiResponse;
import com.socialtech.eppeye.subscription.application.dto.request.PaymentDetailsRequest;
import com.socialtech.eppeye.subscription.application.dto.response.PaymentDetailsResponse;
import com.socialtech.eppeye.subscription.application.services.impl.PaymentDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "PaymentDetails")
@RestController
@RequestMapping("/api/v1/")
public class PaymentDetailsController {

    private final PaymentDetailsService paymentDetailsService;

    public PaymentDetailsController(PaymentDetailsService paymentDetailsService) {
        this.paymentDetailsService = paymentDetailsService;
    }

    @Operation(summary = "Get payment details by id")
    @GetMapping("/payment-details/{id}")
    public ResponseEntity<ApiResponse<PaymentDetailsResponse>> getPaymentDetailsById(@PathVariable Long id) {
        var res = paymentDetailsService.getPaymentRequestById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Get payment details by user id")
    @GetMapping("/payment-details/user/{id}")
    public ResponseEntity<ApiResponse<PaymentDetailsResponse>> getPaymentRequestBySubscriptionId(@PathVariable Long id) {
        var res = paymentDetailsService.getPaymentRequestBySubscriptionId(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Create payment details")
    @PostMapping("/payment-details")
    public ResponseEntity<ApiResponse<PaymentDetailsResponse>> createPaymentDetails(@RequestBody PaymentDetailsRequest request) {
        var res = paymentDetailsService.createPaymentRequest(request);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @Operation(summary = "Update payment details")
    @PutMapping("/payment-details/{id}")
    public ResponseEntity<ApiResponse<PaymentDetailsResponse>> updatePaymentDetails(@PathVariable Long id, @RequestBody PaymentDetailsRequest request) {
        var res = paymentDetailsService.updatePaymentRequest(id, request);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Delete payment details")
    @DeleteMapping("/payment-details/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePaymentDetails(@PathVariable Long id) {
        var res = paymentDetailsService.deletePaymentRequest(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}

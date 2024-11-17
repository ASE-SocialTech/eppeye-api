package com.socialtech.eppeye.subscription.infrastructure.repositories;

import com.socialtech.eppeye.subscription.domain.entities.PaymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IPaymentDetailsRepository extends JpaRepository<PaymentDetails, Long> {

    Optional<PaymentDetails> findBySubscriptionId(Long id);
}

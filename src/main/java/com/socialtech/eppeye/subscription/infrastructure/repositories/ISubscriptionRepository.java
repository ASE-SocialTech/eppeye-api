package com.socialtech.eppeye.subscription.infrastructure.repositories;

import com.socialtech.eppeye.subscription.domain.entities.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ISubscriptionRepository extends JpaRepository<Subscription, Long> {
    Optional<Subscription> findByUserId(Long userId);
}

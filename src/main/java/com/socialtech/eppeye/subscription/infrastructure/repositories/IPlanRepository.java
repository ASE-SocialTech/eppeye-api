package com.socialtech.eppeye.subscription.infrastructure.repositories;

import com.socialtech.eppeye.subscription.domain.entities.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPlanRepository extends JpaRepository<Plan, Long> {
}

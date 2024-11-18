package com.socialtech.eppeye.monitoring.infrastructure.repositories;

import com.socialtech.eppeye.monitoring.domain.entities.ComplianceStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IComplianceStatusRepository extends JpaRepository<ComplianceStatus, Long> {
}

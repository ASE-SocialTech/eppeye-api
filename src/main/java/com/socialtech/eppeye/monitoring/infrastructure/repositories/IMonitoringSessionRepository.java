package com.socialtech.eppeye.monitoring.infrastructure.repositories;

import com.socialtech.eppeye.monitoring.domain.entities.MonitoringSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMonitoringSessionRepository extends JpaRepository<MonitoringSession, Long> {
}

package com.socialtech.eppeye.monitoring.infrastructure.repositories;


import com.socialtech.eppeye.monitoring.domain.entities.MonitoringIncident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMonitoringIncidentRepository extends JpaRepository<MonitoringIncident, Long> {
}

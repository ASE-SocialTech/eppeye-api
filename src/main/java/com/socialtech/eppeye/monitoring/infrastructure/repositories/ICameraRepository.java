package com.socialtech.eppeye.monitoring.infrastructure.repositories;

import com.socialtech.eppeye.monitoring.domain.entities.Camera;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICameraRepository extends JpaRepository<Camera, Long> {
}
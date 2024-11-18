package com.socialtech.eppeye.monitoring.infrastructure.repositories;

import com.socialtech.eppeye.monitoring.domain.entities.Area;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAreaRepository extends JpaRepository<Area, Long> {

}

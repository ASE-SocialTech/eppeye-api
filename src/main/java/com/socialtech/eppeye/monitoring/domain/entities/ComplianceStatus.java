package com.socialtech.eppeye.monitoring.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "compliance_status")
public class ComplianceStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean isCompliant;
    private String description;

    @OneToOne
    @JoinColumn(name = "monitoring_session_id")
    private MonitoringSession monitoringSession;
}

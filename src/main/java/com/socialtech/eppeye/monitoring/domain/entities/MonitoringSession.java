package com.socialtech.eppeye.monitoring.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "monitoring_sessions")
public class MonitoringSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date startTime;
    private Date endTime;
    private String status;

    @ManyToOne
    @JoinColumn(name = "camera_id")
    private Camera camera;

    @OneToMany(mappedBy = "monitoringSession", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MonitoringIncident> incidents;

    @OneToOne(mappedBy = "monitoringSession", cascade = CascadeType.ALL, orphanRemoval = true)
    private ComplianceStatus complianceStatus;

}
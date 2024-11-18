package com.socialtech.eppeye.monitoring.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "monitoring_incidents")
public class MonitoringIncident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String incidentType;
    private Date detectedTime;
    private String description;

    @ManyToOne
    @JoinColumn(name = "monitoring_session_id")
    private MonitoringSession monitoringSession;
}

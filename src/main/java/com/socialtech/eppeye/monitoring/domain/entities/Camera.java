package com.socialtech.eppeye.monitoring.domain.entities;

import com.socialtech.eppeye.account.domain.entities.User;
import com.socialtech.eppeye.configuration.domain.entities.PPEConfiguration;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "cameras")
public class Camera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cameraDevice;

    @ManyToOne
    @JoinColumn(name = "area_id")
    private Area area;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "camera")
    private List<MonitoringSession> monitoringSessions;

    @OneToMany(mappedBy = "camera")
    private List<PPEConfiguration> ppeConfigurations;
}

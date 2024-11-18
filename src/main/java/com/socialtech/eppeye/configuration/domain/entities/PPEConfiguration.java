package com.socialtech.eppeye.configuration.domain.entities;

import com.socialtech.eppeye.monitoring.domain.entities.Camera;
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
@Table(name = "ppe_configurations")
public class PPEConfiguration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String areaId;
    private String roleId;

    @ManyToOne
    @JoinColumn(name = "camera_id")
    private Camera camera;

    @OneToMany(mappedBy = "ppeConfiguration")
    private List<PPERequirements> ppeRequirements;
}


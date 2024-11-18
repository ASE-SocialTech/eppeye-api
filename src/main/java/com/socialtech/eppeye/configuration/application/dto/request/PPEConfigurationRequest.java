package com.socialtech.eppeye.configuration.application.dto.request;

import com.socialtech.eppeye.monitoring.domain.entities.Camera;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PPEConfigurationRequest {
    private Long id;
    private String areaId;
    private String roleId;
    private Long cameraId;
}

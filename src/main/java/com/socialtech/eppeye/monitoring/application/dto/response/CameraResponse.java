package com.socialtech.eppeye.monitoring.application.dto.response;

import com.socialtech.eppeye.configuration.application.dto.response.PPEConfigurationResponse;
import com.socialtech.eppeye.monitoring.domain.entities.Area;
import com.socialtech.eppeye.account.domain.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CameraResponse {

    private Long id;
    private String cameraDevice;
    private List<MonitoringSessionResponse> monitoringSessions;
    private List<PPEConfigurationResponse> ppeConfigurations;
}

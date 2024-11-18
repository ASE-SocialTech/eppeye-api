package com.socialtech.eppeye.configuration.application.dto.response;

import com.socialtech.eppeye.monitoring.application.dto.response.CameraResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PPEConfigurationResponse {
    private Long id;
    private String areaId;
    private String roleId;
    private CameraResponse camera;
    private List<PPERequirementsResponse> ppeRequirements;
}

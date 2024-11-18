package com.socialtech.eppeye.monitoring.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CameraRequest {

    private String cameraDevice;
    private Long area;
    private Long user;
}

package com.socialtech.eppeye.monitoring.application.dto.response;

import com.socialtech.eppeye.monitoring.domain.entities.Camera;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AreaResponse {

    private Long id;
    private String name;
    private String description;
    private List<CameraResponse> cameras;
}
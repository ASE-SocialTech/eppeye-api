package com.socialtech.eppeye.monitoring.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MonitoringSessionRequest {

    private Date startTime;
    private Date endTime;
    private String status;
    private Long cameraId;
}

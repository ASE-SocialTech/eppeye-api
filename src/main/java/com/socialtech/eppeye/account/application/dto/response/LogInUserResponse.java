package com.socialtech.eppeye.account.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LogInUserResponse {
    private Long id;
    private String username;
    private String email;
    private String token;
}

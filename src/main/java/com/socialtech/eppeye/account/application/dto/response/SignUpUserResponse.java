package com.socialtech.eppeye.account.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpUserResponse {
    private String email;
    private String username;
    private String status;
    private String role;
}

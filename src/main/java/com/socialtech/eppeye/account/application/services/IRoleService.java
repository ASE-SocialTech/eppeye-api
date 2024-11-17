package com.socialtech.eppeye.account.application.services;

import com.socialtech.eppeye.account.application.dto.request.RoleRequest;
import com.socialtech.eppeye.account.application.dto.response.RoleResponse;
import com.socialtech.eppeye.shared.model.dto.response.ApiResponse;

public interface IRoleService {
    ApiResponse<RoleResponse> getRoleById(Long id);

    ApiResponse<RoleResponse> createRole(RoleRequest roleRequestDto);

    ApiResponse<RoleResponse> updateRole(Long id, RoleRequest roleRequestDto);

    ApiResponse<Void> deleteRole(Long id);
}

package com.socialtech.eppeye.account.application.services.impl;

import com.socialtech.eppeye.account.application.dto.request.RoleRequest;
import com.socialtech.eppeye.account.application.dto.response.RoleResponse;
import com.socialtech.eppeye.account.application.services.IRoleService;
import com.socialtech.eppeye.account.domain.entities.Role;
import com.socialtech.eppeye.account.infrastructure.repositories.IRoleRepository;
import com.socialtech.eppeye.shared.model.dto.response.ApiResponse;
import com.socialtech.eppeye.shared.model.enums.Estatus;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService implements IRoleService {
    private final IRoleRepository roleRepository;
    private final ModelMapper modelMapper;

    public RoleService(IRoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ApiResponse<RoleResponse> getRoleById(Long id) {
        Optional<Role> roleOptional = roleRepository.findById(id);

        if (roleOptional.isEmpty()){
            return new ApiResponse<>("Role not found", Estatus.ERROR, null);
        } else {
            Role role = roleOptional.get();
            var roleResponseDto = modelMapper.map(role, RoleResponse.class);
            return new ApiResponse<>("Role found successfully", Estatus.SUCCESS, roleResponseDto);
        }
    }

    @Override
    public ApiResponse<RoleResponse> createRole(RoleRequest request) {
        var role = modelMapper.map(request, Role.class);
        roleRepository.save(role);
        var roleResponseDto = modelMapper.map(role, RoleResponse.class);
        return new ApiResponse<>("Role created successfully", Estatus.SUCCESS, roleResponseDto);
    }

    @Override
    public ApiResponse<RoleResponse> updateRole(Long id, RoleRequest request) {
        Optional<Role> roleOptional = roleRepository.findById(id);

        if (roleOptional.isEmpty()){
            return new ApiResponse<>("Role not found", Estatus.ERROR, null);
        } else {
            Role role = roleOptional.get();
            modelMapper.map(request, role);
            roleRepository.save(role);
            RoleResponse roleResponse = modelMapper.map(role, RoleResponse.class);
            return new ApiResponse<>("Role updated successfully", Estatus.SUCCESS, roleResponse);
        }
    }

    @Override
    public ApiResponse<Void> deleteRole(Long id) {
        Optional<Role> roleOptional = roleRepository.findById(id);

        if (roleOptional.isEmpty()){
            return new ApiResponse<>("Role not found", Estatus.ERROR, null);
        } else {
            roleRepository.deleteById(id);
            return new ApiResponse<>("Role deleted successfully", Estatus.SUCCESS, null);
        }
    }
}

package com.socialtech.eppeye.account.application.controller;

import com.socialtech.eppeye.account.application.dto.request.RoleRequest;
import com.socialtech.eppeye.account.application.dto.response.RoleResponse;
import com.socialtech.eppeye.account.application.services.impl.RoleService;
import com.socialtech.eppeye.shared.model.dto.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SecurityRequirements
@Tag(name = "Roles")
@RestController
@RequestMapping("/api/v1/")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @Operation(summary = "Get role by id")
    @GetMapping("/roles/{id}")
    public ResponseEntity<ApiResponse<RoleResponse>> getRoleById(@PathVariable Long id) {
        var res = roleService.getRoleById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Create role")
    @PostMapping("/roles")
    public ResponseEntity<ApiResponse<RoleResponse>> createRole(@RequestBody RoleRequest roleRequestDto) {
        var res = roleService.createRole(roleRequestDto);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Update role")
    @PutMapping("/roles/{id}")
    public ResponseEntity<ApiResponse<RoleResponse>> updateRole(@PathVariable Long id, @RequestBody RoleRequest roleRequestDto) {
        var res = roleService.updateRole(id, roleRequestDto);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Delete role")
    @DeleteMapping("/roles/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteRole(@PathVariable Long id) {
        var res = roleService.deleteRole(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}

package com.socialtech.eppeye.account.application.controller;

import com.socialtech.eppeye.account.application.dto.request.LogInUserRequest;
import com.socialtech.eppeye.account.application.dto.request.SignUpUserRequest;
import com.socialtech.eppeye.account.application.dto.response.SignUpUserResponse;
import com.socialtech.eppeye.account.application.services.IUserService;
import com.socialtech.eppeye.shared.model.dto.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Account")
@RestController
@RequestMapping("/api/v1/accounts")
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @SecurityRequirements
    @Operation(summary = "Log in")
    @PostMapping("/auth/logIn")
    public ResponseEntity<?> signInUser(@Valid @RequestBody LogInUserRequest request) {
        var res = userService.logIn(request);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @SecurityRequirements
    @Operation(summary = "Create a new user")
    @PostMapping("/auth/signUp")
    public ResponseEntity<ApiResponse<SignUpUserResponse>> signUp(@Valid @RequestBody SignUpUserRequest request) {
        var res = userService.signUp(request);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Delete a user")
    @PutMapping("/auth/delete/{userId}")
    public ResponseEntity<ApiResponse<Object>> deleteUserById(@PathVariable Long userId){
        var res = userService.deleteUserById(userId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}

package com.socialtech.eppeye.account.application.services;

import com.socialtech.eppeye.account.application.dto.request.LogInUserRequest;
import com.socialtech.eppeye.account.application.dto.request.SignUpUserRequest;
import com.socialtech.eppeye.account.application.dto.response.LogInUserResponse;
import com.socialtech.eppeye.account.application.dto.response.SignUpUserResponse;
import com.socialtech.eppeye.account.domain.entities.User;
import com.socialtech.eppeye.shared.model.dto.response.ApiResponse;

import java.util.Optional;

public interface IUserService {
    ApiResponse<LogInUserResponse> logIn(LogInUserRequest request);
    ApiResponse<SignUpUserResponse> signUp(SignUpUserRequest request);
    ApiResponse<Object> deleteUserById(Long userId);
}

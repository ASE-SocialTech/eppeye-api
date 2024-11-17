package com.socialtech.eppeye.account.application.services.impl;

import com.socialtech.eppeye.account.application.dto.request.LogInUserRequest;
import com.socialtech.eppeye.account.application.dto.request.SignUpUserRequest;
import com.socialtech.eppeye.account.application.dto.response.LogInUserResponse;
import com.socialtech.eppeye.account.application.dto.response.SignUpUserResponse;
import com.socialtech.eppeye.account.application.services.IUserService;
import com.socialtech.eppeye.account.domain.entities.User;
import com.socialtech.eppeye.account.domain.jwt.provider.JwtTokenProvider;
import com.socialtech.eppeye.account.infrastructure.repositories.IRoleRepository;
import com.socialtech.eppeye.account.infrastructure.repositories.IUserRepository;
import com.socialtech.eppeye.shared.exception.ApplicationException;
import com.socialtech.eppeye.shared.model.dto.response.ApiResponse;
import com.socialtech.eppeye.shared.model.dto.response.ResourceNotFoundException;
import com.socialtech.eppeye.shared.model.enums.Estatus;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService implements IUserService {
    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public UserService(IUserRepository userRepository, IRoleRepository roleRepository, ModelMapper modelMapper, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }
    @Override
    public ApiResponse<LogInUserResponse> logIn(LogInUserRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);
        var authenticatedUser = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();

        var authenticatedUserData = userRepository.findByEmail(authenticatedUser.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("No user with given email was found"));

        var userResponseDto = modelMapper.map(authenticatedUserData, LogInUserResponse.class);
        userResponseDto.setToken(token);

        return new ApiResponse<>("OK", Estatus.SUCCESS, userResponseDto);
    }

    @Override
    public ApiResponse<SignUpUserResponse> signUp(SignUpUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ApplicationException(HttpStatus.BAD_REQUEST, "Email is already registered");
        }
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new ApplicationException(HttpStatus.BAD_REQUEST, "Username is already registered");
        }

        request.setPassword(passwordEncoder.encode(request.getPassword()));
        var user = modelMapper.map(request, User.class);
        user.setStatus("enabled");

        var roles = roleRepository.getRoleById(request.getRole());
        user.setRoles(Collections.singleton(roles));

        var createdUser = userRepository.save(user);
        var userResponse = modelMapper.map(createdUser, SignUpUserResponse.class);

        return new ApiResponse<>("User was successfully created", Estatus.SUCCESS, userResponse);

    }

    @Override
    public ApiResponse<Object> deleteUserById(Long userId) {
        return null;
    }

}

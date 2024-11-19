package com.socialtech.eppeye.monitoring.application.services.impl;

import com.socialtech.eppeye.account.domain.entities.User;
import com.socialtech.eppeye.account.infrastructure.repositories.IUserRepository;
import com.socialtech.eppeye.monitoring.application.dto.request.CameraRequest;
import com.socialtech.eppeye.monitoring.application.dto.response.CameraResponse;
import com.socialtech.eppeye.monitoring.application.services.ICameraService;
import com.socialtech.eppeye.monitoring.domain.entities.Area;
import com.socialtech.eppeye.monitoring.domain.entities.Camera;
import com.socialtech.eppeye.monitoring.infrastructure.repositories.IAreaRepository;
import com.socialtech.eppeye.monitoring.infrastructure.repositories.ICameraRepository;
import com.socialtech.eppeye.shared.model.dto.response.ApiResponse;
import com.socialtech.eppeye.shared.model.enums.Estatus;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CameraService implements ICameraService {
    private final ICameraRepository cameraRepository;
    private final IAreaRepository areaRepository;
    private final IUserRepository userRepository;
    private final ModelMapper modelMapper;

    // Constructor inyectado con @Autowired por defecto en Spring
    public CameraService(
            ICameraRepository cameraRepository,
            IAreaRepository areaRepository,
            IUserRepository userRepository,
            ModelMapper modelMapper) {
        this.cameraRepository = cameraRepository;
        this.areaRepository = areaRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ApiResponse<CameraResponse> getCameraById(Long id) {
        return cameraRepository.findById(id)
                .map(camera -> {
                    CameraResponse cameraResponseDto = modelMapper.map(camera, CameraResponse.class);
                    return new ApiResponse<>("Camera found successfully", Estatus.SUCCESS, cameraResponseDto);
                })
                .orElseGet(() -> new ApiResponse<>("Camera not found", Estatus.ERROR, null));
    }

    @Override
    public ApiResponse<List<CameraResponse>> getByUserId(Long userId) {
        List<Camera> cameras = cameraRepository.findByUserId(userId);

        List<CameraResponse> cameraResponseDtos = modelMapper.map(cameras, new TypeToken<List<CameraResponse>>(){}.getType());

        return new ApiResponse<>("Cameras found successfully", Estatus.SUCCESS, cameraResponseDtos);
    }

    @Override
    public ApiResponse<CameraResponse> createCamera(CameraRequest request) {
        Optional<Area> areaOptional = areaRepository.findById(request.getArea());
        Optional<User> userOptional = userRepository.findById(request.getUser());

        if (areaOptional.isEmpty()) {
            return new ApiResponse<>("Area not found", Estatus.ERROR, null);
        }
        if (userOptional.isEmpty()) {
            return new ApiResponse<>("User not found", Estatus.ERROR, null);
        }

        Camera camera = modelMapper.map(request, Camera.class);
        camera.setArea(areaOptional.get());
        camera.setUser(userOptional.get());
        cameraRepository.save(camera);

        CameraResponse cameraResponseDto = modelMapper.map(camera, CameraResponse.class);
        return new ApiResponse<>("Camera created successfully", Estatus.SUCCESS, cameraResponseDto);
    }

    @Override
    public ApiResponse<CameraResponse> updateCamera(Long id, CameraRequest request) {
        Optional<Camera> cameraOptional = cameraRepository.findById(id);
        Optional<Area> areaOptional = areaRepository.findById(request.getArea());
        Optional<User> userOptional = userRepository.findById(request.getUser());

        if (cameraOptional.isEmpty()) {
            return new ApiResponse<>("Camera not found", Estatus.ERROR, null);
        }
        if (areaOptional.isEmpty()) {
            return new ApiResponse<>("Area not found", Estatus.ERROR, null);
        }
        if (userOptional.isEmpty()) {
            return new ApiResponse<>("User not found", Estatus.ERROR, null);
        }

        Camera camera = cameraOptional.get();
        camera.setCameraDevice(request.getCameraDevice());
        camera.setArea(areaOptional.get());
        camera.setUser(userOptional.get());

        cameraRepository.save(camera);

        CameraResponse cameraResponseDto = modelMapper.map(camera, CameraResponse.class);
        return new ApiResponse<>("Camera updated successfully", Estatus.SUCCESS, cameraResponseDto);
    }

    @Override
    public ApiResponse<Void> deleteCamera(Long id) {
        if (!cameraRepository.existsById(id)) {
            return new ApiResponse<>("Camera not found", Estatus.ERROR, null);
        }

        cameraRepository.deleteById(id);
        return new ApiResponse<>("Camera deleted successfully", Estatus.SUCCESS, null);
    }
}

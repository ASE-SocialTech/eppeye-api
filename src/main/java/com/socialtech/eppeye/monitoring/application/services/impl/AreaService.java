package com.socialtech.eppeye.monitoring.application.services.impl;

import com.socialtech.eppeye.monitoring.application.dto.request.AreaRequest;
import com.socialtech.eppeye.monitoring.application.dto.response.AreaResponse;
import com.socialtech.eppeye.monitoring.application.services.IAreaService;
import com.socialtech.eppeye.monitoring.domain.entities.Area;
import com.socialtech.eppeye.monitoring.infrastructure.repositories.IAreaRepository;
import com.socialtech.eppeye.shared.model.dto.response.ApiResponse;
import com.socialtech.eppeye.shared.model.enums.Estatus;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AreaService implements IAreaService {

    private final IAreaRepository areaRepository;
    private final ModelMapper modelMapper;

    private AreaService(IAreaRepository areaRepository, ModelMapper modelMapper) {
        this.areaRepository = areaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ApiResponse<AreaResponse> getAreaById(Long id) {
        Optional<Area> areaOptional = areaRepository.findById(id);
        if (areaOptional.isEmpty()) {
            return new ApiResponse<>("Area not found", Estatus.ERROR, null);
        } else {
            Area area = areaOptional.get();
            var areaResponseDto = modelMapper.map(area, AreaResponse.class);
            return new ApiResponse<>("Area found successfully", Estatus.SUCCESS,areaResponseDto);
        }
    }

    @Override
    public ApiResponse<AreaResponse> createArea(AreaRequest request) {
        var area = modelMapper.map(request, Area.class);
        areaRepository.save(area);
        var areaResponseDto = modelMapper.map(area, AreaResponse.class);
        return new ApiResponse<>("Area created successfully", Estatus.SUCCESS, areaResponseDto);
    }

    @Override
    public ApiResponse<AreaResponse> updateArea(Long id, AreaRequest request) {
        Optional<Area> areaOptional = areaRepository.findById(id);

        if (areaOptional.isEmpty()) {
            return new ApiResponse<>("Area not found", Estatus.ERROR, null);
        } else {
            Area area = areaOptional.get();
            modelMapper.map(request, area);
            areaRepository.save(area);
            AreaResponse areaResponse = modelMapper.map(area, AreaResponse.class);
            return new ApiResponse<>("Area updated successfully", Estatus.SUCCESS, areaResponse);
        }
    }

    @Override
    public ApiResponse<Void> deleteArea(Long id) {
        Optional<Area> areaOptional = areaRepository.findById(id);
        if (areaOptional.isEmpty()) {
            return new ApiResponse<>("Area not found", Estatus.ERROR, null);
        } else {
            areaRepository.deleteById(id);
            return new ApiResponse<>("Area deleted successfully", Estatus.SUCCESS, null);
        }
    }
}

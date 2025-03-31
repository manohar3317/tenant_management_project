package com.maintenanceservice.service;

import com.maintenanceservice.dto.MaintenanceRequestDTO;
import com.maintenanceservice.dto.MaintenanceResponseDTO;
import com.maintenanceservice.model.MaintenanceRequest;
import com.maintenanceservice.model.RequestStatus;
import com.maintenanceservice.repository.MaintenanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MaintenanceService {

    private final MaintenanceRepository maintenanceRepository;

    public MaintenanceResponseDTO submitRequest(MaintenanceRequestDTO requestDTO) {
        MaintenanceRequest request = MaintenanceRequest.builder()
                .tenantId(requestDTO.getTenantId())
                .description(requestDTO.getDescription())
                .status(RequestStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .build();

        return mapToDTO(maintenanceRepository.save(request));
    }

    public List<MaintenanceResponseDTO> getRequestsByTenant(Long tenantId) {
        return maintenanceRepository.findByTenantId(tenantId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public MaintenanceResponseDTO updateStatus(Long requestId, RequestStatus status) {
        MaintenanceRequest request = maintenanceRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        request.setStatus(status);
        return mapToDTO(maintenanceRepository.save(request));
    }

    private MaintenanceResponseDTO mapToDTO(MaintenanceRequest request) {
        return MaintenanceResponseDTO.builder()
                .id(request.getId())
                .tenantId(request.getTenantId())
                .description(request.getDescription())
                .status(request.getStatus())
                .createdAt(request.getCreatedAt())
                .build();
    }
}

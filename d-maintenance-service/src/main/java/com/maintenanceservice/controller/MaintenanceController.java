package com.maintenanceservice.controller;

import com.maintenanceservice.dto.MaintenanceRequestDTO;
import com.maintenanceservice.dto.MaintenanceResponseDTO;
import com.maintenanceservice.model.RequestStatus;
import com.maintenanceservice.service.MaintenanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maintenance")
@RequiredArgsConstructor
public class MaintenanceController {

    private final MaintenanceService maintenanceService;

    @PostMapping("/request/create")
    public ResponseEntity<MaintenanceResponseDTO> createRequest(@RequestBody MaintenanceRequestDTO requestDTO) {
        return ResponseEntity.ok(maintenanceService.submitRequest(requestDTO));
    }

    @GetMapping("/request/status/{tenantId}")
    public ResponseEntity<List<MaintenanceResponseDTO>> getRequests(@PathVariable Long tenantId) {
        return ResponseEntity.ok(maintenanceService.getRequestsByTenant(tenantId));
    }

    @PutMapping("/request/update/{requestId}")
    public ResponseEntity<MaintenanceResponseDTO> updateStatus(
            @PathVariable Long requestId,
            @RequestParam RequestStatus status
    ) {
        return ResponseEntity.ok(maintenanceService.updateStatus(requestId, status));
    }
}

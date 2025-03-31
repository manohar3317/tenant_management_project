package com.leaseservice.controller;

import com.leaseservice.dto.LeaseRequest;
import com.leaseservice.dto.LeaseResponse;
import com.leaseservice.service.LeaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/leases")
@RequiredArgsConstructor
public class LeaseController {

    private final LeaseService leaseService;

    @PostMapping("/create")
    public ResponseEntity<LeaseResponse> create(@RequestBody LeaseRequest request) {
        return ResponseEntity.ok(leaseService.createLease(request));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<LeaseResponse> update(@PathVariable Long id, @RequestBody LeaseRequest request) {
        return ResponseEntity.ok(leaseService.updateLease(id, request));
    }

    @PutMapping("/renew/{id}")
    public ResponseEntity<LeaseResponse> renew(@PathVariable Long id) {
        return ResponseEntity.ok(leaseService.renewLease(id));
    }
}

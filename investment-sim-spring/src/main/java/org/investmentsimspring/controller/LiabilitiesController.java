package org.investmentsimspring.controller;

import org.investmentsimspring.domain.liabilities.CreateLiabilityDto;
import org.investmentsimspring.domain.liabilities.LiabilitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/liabilities")
public class LiabilitiesController {

    private final LiabilitiesService service;

    @Autowired
    public LiabilitiesController(LiabilitiesService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getAllLiabilities() {
        try {
            return ResponseEntity.ok(service.getAllLiabilities());
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLiability(@PathVariable long id) {
        try {
            return ResponseEntity.ok(service.getLiability(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @PostMapping()
    public ResponseEntity<?> createLiability(@RequestBody CreateLiabilityDto dto) {
        try {
            return ResponseEntity.ok(service.createLiability(dto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLiability(@PathVariable long id) {
        try {
            return ResponseEntity.ok(service.deleteLiability(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }
}

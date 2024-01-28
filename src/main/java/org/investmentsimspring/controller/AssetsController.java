package org.investmentsimspring.controller;

import org.investmentsimspring.domain.assets.AssetsService;
import org.investmentsimspring.domain.assets.CreateAssetDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/assets")
public class AssetsController {

    private final AssetsService service;

    @Autowired
    public AssetsController(AssetsService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getAllAssets() {
        try {
            return ResponseEntity.ok(service.getAllAssets());
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
    public ResponseEntity<?> getAsset(@PathVariable long id) {
        try {
            return ResponseEntity.ok(service.getAsset(id));
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
    public ResponseEntity<?> createAsset(@RequestBody CreateAssetDto dto) {
        try {
            return ResponseEntity.ok(service.createAsset(dto));
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

    @PostMapping(params = "simulationId")
    public ResponseEntity<?> createAssetInSimulation(@RequestParam long simulationId, @RequestBody CreateAssetDto dto) {
        try {
            return ResponseEntity.ok(service.createAssetInSimulation(simulationId, dto));
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
    public ResponseEntity<?> deleteAsset(@PathVariable long id) {
        try {
            return ResponseEntity.ok(service.deleteAsset(id));
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

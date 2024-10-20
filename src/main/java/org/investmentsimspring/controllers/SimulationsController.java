package org.investmentsimspring.controllers;

import org.investmentsimspring.models.simulations.CreateSimulationDto;
import org.investmentsimspring.services.SimulationsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/simulations")
public class SimulationsController {

    private final SimulationsService service;

    public SimulationsController(SimulationsService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getAllSimulations() {
        try {
            return ResponseEntity.ok(service.getAllSimulations());
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
    public ResponseEntity<?> getSimulation(@PathVariable long id) {
        try {
            return ResponseEntity.ok(service.getSimulation(id));
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

    @GetMapping(value = "", params = "userId")
    public ResponseEntity<?> getSimulationsByUserId(@RequestParam long userId) {
        try {
            return ResponseEntity.ok(service.getSimulationsByUserId(userId));
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

    @PostMapping
    public ResponseEntity<?> createSimulation(@RequestBody CreateSimulationDto dto) {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(service.createSimulation(dto));
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
    public ResponseEntity<?> deleteSimulation(@PathVariable long id) {
        try {
            return ResponseEntity.ok(service.deleteSimulation(id));
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

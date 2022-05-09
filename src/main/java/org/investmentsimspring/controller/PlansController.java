package org.investmentsimspring.controller;

import org.investmentsimspring.domain.plans.CreatePlanDto;
import org.investmentsimspring.domain.plans.PlansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/plans")
public class PlansController {

    private final PlansService service;

    @Autowired
    public PlansController(PlansService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getAllPlans() {
        try {
            return ResponseEntity.ok(service.getAllPlans());
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
    public ResponseEntity<?> getPlan(@PathVariable long id) {
        try {
            return ResponseEntity.ok(service.getPlan(id));
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
    public ResponseEntity<?> getPlansByUserId(@RequestParam long userId) {
        try {
            return ResponseEntity.ok(service.getPlansByUserId(userId));
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
    public ResponseEntity<?> createPlan(@RequestBody CreatePlanDto dto) {
        try {
            return ResponseEntity.ok(service.createPlan(dto));
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
    public ResponseEntity<?> deletePlan(@PathVariable long id) {
        try {
            return ResponseEntity.ok(service.deletePlan(id));
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

package org.investmentsimspring.controller;

import org.investmentsimspring.domain.plans.CreatePlanDto;
import org.investmentsimspring.domain.plans.PlanDto;
import org.investmentsimspring.domain.plans.PlansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = "/api/plans")
public class PlansController {

    private final PlansService service;

    @Autowired
    public PlansController(PlansService service) {
        this.service = service;
    }

    @GetMapping
    public List<PlanDto> getAllPlans() {
        return service.getAllPlans();
    }

    @PostMapping
    public PlanDto createPlan(@RequestBody CreatePlanDto dto) {
        try {
            return service.createPlan(dto);
        } catch (IllegalArgumentException | IllegalStateException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

}

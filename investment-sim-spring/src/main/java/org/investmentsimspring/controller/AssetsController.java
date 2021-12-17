package org.investmentsimspring.controller;

import org.investmentsimspring.domain.assets.AssetDto;
import org.investmentsimspring.domain.assets.AssetsService;
import org.investmentsimspring.domain.assets.CreateAssetDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = "/api/assets")
public class AssetsController {

    private final AssetsService service;

    @Autowired
    public AssetsController(AssetsService service) {
        this.service = service;
    }

    @GetMapping
    public List<AssetDto> getAllAssets() {
        return service.getAllAssets();
    }

    @PostMapping
    public AssetDto createAsset(@RequestBody CreateAssetDto dto) {
        try {
            return service.createAsset(dto);
        } catch (IllegalArgumentException | IllegalStateException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

}

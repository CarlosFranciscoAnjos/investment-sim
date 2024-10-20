package org.investmentsimspring.domain.assets;

import org.investmentsimspring.database.AssetsRepository;
import org.investmentsimspring.database.SimulationsRepository;
import org.investmentsimspring.domain.simulations.Simulation;
import org.investmentsimspring.models.assets.AssetDto;
import org.investmentsimspring.models.assets.CreateAssetDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetsService {

    private final AssetsRepository assetsRepo;
    private final SimulationsRepository simulationsRepo;

    public AssetsService(AssetsRepository repo, SimulationsRepository simulationsRepo) {
        this.assetsRepo = repo;
        this.simulationsRepo = simulationsRepo;
    }

    public List<AssetDto> getAllAssets() {
        return assetsRepo.findAll().stream()
                .map(Asset::toDto)
                .toList();
    }

    public AssetDto getAsset(long id) {
        Asset asset = assetsRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Asset with id %d not found", id)));
        return asset.toDto();
    }

    public AssetDto createAsset(CreateAssetDto dto) {
        // fetch container
        Simulation simulation = simulationsRepo.findById(dto.containerId)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Container with id %d not found", dto.containerId)));
        // asset builder
        AssetBuilder builder = new AssetBuilder();
        builder.description(dto.description);
        builder.income(dto.income);
        builder.value(dto.value);
        builder.simulation(simulation);
        // save in repo
        Asset asset = assetsRepo.save(builder.build());
        return asset.toDto();
    }

    public AssetDto createAssetInSimulation(long simulationId, CreateAssetDto dto) {
        // fetch simulation
        Simulation simulation = simulationsRepo.findById(simulationId)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Simulation with id %d not found", simulationId)));
        // update dto and call default create asset
        dto.containerId = simulation.getId();
        return createAsset(dto);
    }

    public AssetDto deleteAsset(long id) {
        Asset asset = assetsRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Asset with id %d not found", id)));
        assetsRepo.delete(asset);
        return asset.toDto();
    }
}

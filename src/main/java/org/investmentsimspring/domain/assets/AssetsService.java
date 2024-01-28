package org.investmentsimspring.domain.assets;

import org.investmentsimspring.domain.containers.Container;
import org.investmentsimspring.domain.simulations.Simulation;
import org.investmentsimspring.infrastructure.AssetsRepository;
import org.investmentsimspring.infrastructure.ContainersRepository;
import org.investmentsimspring.infrastructure.SimulationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetsService {

    private final AssetsRepository assetsRepo;
    private final ContainersRepository containersRepo;
    private final SimulationsRepository simulationsRepo;

    @Autowired
    public AssetsService(AssetsRepository repo, ContainersRepository containersRepo, SimulationsRepository simulationsRepo) {
        this.assetsRepo = repo;
        this.containersRepo = containersRepo;
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
        Container container = containersRepo.findById(dto.containerId)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Container with id %d not found", dto.containerId)));
        // asset builder
        AssetBuilder builder = new AssetBuilder();
        builder.description(dto.description);
        builder.income(dto.income);
        builder.value(dto.value);
        builder.container(container);
        // save in repo
        Asset asset = assetsRepo.save(builder.build());
        return asset.toDto();
    }

    public AssetDto createAssetInSimulation(long simulationId, CreateAssetDto dto) {
        // fetch simulation
        Simulation simulation = simulationsRepo.findById(simulationId)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Simulation with id %d not found", simulationId)));
        // update dto and call default create asset
        dto.containerId = simulation.getContainer().getId();
        return createAsset(dto);
    }

    public AssetDto deleteAsset(long id) {
        Asset asset = assetsRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Asset with id %d not found", id)));
        assetsRepo.delete(asset);
        return asset.toDto();
    }
}

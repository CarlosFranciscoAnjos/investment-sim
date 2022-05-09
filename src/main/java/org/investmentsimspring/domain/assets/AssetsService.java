package org.investmentsimspring.domain.assets;

import org.investmentsimspring.domain.containers.Container;
import org.investmentsimspring.domain.plans.Plan;
import org.investmentsimspring.infrastructure.AssetsRepository;
import org.investmentsimspring.infrastructure.ContainersRepository;
import org.investmentsimspring.infrastructure.PlansRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetsService {

    private final AssetsRepository assetsRepo;
    private final ContainersRepository containersRepo;
    private final PlansRepository plansRepo;

    @Autowired
    public AssetsService(AssetsRepository repo, ContainersRepository containersRepo, PlansRepository plansRepo) {
        this.assetsRepo = repo;
        this.containersRepo = containersRepo;
        this.plansRepo = plansRepo;
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

    public AssetDto createAssetInPlan(long planId, CreateAssetDto dto) {
        // fetch plan
        Plan plan = plansRepo.findById(planId)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Plan with id %d not found", planId)));
        // update dto and call default create asset
        dto.containerId = plan.getContainer().getId();
        return createAsset(dto);
    }

    public AssetDto deleteAsset(long id) {
        Asset asset = assetsRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Asset with id %d not found", id)));
        assetsRepo.delete(asset);
        return asset.toDto();
    }
}

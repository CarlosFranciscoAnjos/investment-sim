package org.investmentsimspring.domain.assets;

import org.investmentsimspring.domain.plans.Plan;
import org.investmentsimspring.infrastructure.AssetsRepository;
import org.investmentsimspring.infrastructure.PlansRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssetsService {

    private final AssetsRepository assetsRepo;
    private final PlansRepository plansRepo;

    @Autowired
    public AssetsService(AssetsRepository repo, PlansRepository plansRepo) {
        this.assetsRepo = repo;
        this.plansRepo = plansRepo;
    }

    public List<AssetDto> getAllAssets() {
        return assetsRepo.findAll().stream()
                .map(Asset::toDto)
                .collect(Collectors.toList());
    }

    public AssetDto createAsset(CreateAssetDto dto) {
        // fetch plan
        Plan plan = plansRepo.findById(dto.planId)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Plan with id %d not found", dto.planId)));
        // asset builder
        AssetBuilder builder = new AssetBuilder();
        builder.description(dto.description);
        builder.income(dto.income);
        builder.value(dto.value);
        builder.plan(plan);
        // save in repo
        Asset asset = assetsRepo.save(builder.build());
        return asset.toDto();
    }
}

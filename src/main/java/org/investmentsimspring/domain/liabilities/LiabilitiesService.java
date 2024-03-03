package org.investmentsimspring.domain.liabilities;

import org.investmentsimspring.database.AssetsRepository;
import org.investmentsimspring.database.LiabilitiesRepository;
import org.investmentsimspring.domain.assets.Asset;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LiabilitiesService {

    private final LiabilitiesRepository liabilitiesRepo;
    private final AssetsRepository assetsRepo;

    public LiabilitiesService(LiabilitiesRepository liabilitiesRepo, AssetsRepository assetsRepo) {
        this.liabilitiesRepo = liabilitiesRepo;
        this.assetsRepo = assetsRepo;
    }

    public List<LiabilityDto> getAllLiabilities() {
        return liabilitiesRepo.findAll().stream()
                .map(Liability::toDto)
                .toList();
    }

    public LiabilityDto getLiability(long id) {
        Liability liability = liabilitiesRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Liability with id %d not found", id)));
        return liability.toDto();
    }

    public LiabilityDto createLiability(CreateLiabilityDto dto) {
        // fetch asset
        Asset asset = assetsRepo.findById(dto.assetId)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Asset with id %d not found", dto.assetId)));
        // asset builder
        LiabilityBuilder builder = new LiabilityBuilder();
        builder.description(dto.description);
        builder.expense(dto.expense);
        builder.asset(asset);
        // save in repo
        Liability liability = liabilitiesRepo.save(builder.build());
        return liability.toDto();
    }

    public LiabilityDto deleteLiability(long id) {
        Liability liability = liabilitiesRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Liability with id %d not found", id)));
        liabilitiesRepo.delete(liability);
        return liability.toDto();
    }
}

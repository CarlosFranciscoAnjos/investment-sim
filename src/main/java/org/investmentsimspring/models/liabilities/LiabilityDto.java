package org.investmentsimspring.models.liabilities;

import org.investmentsimspring.domain.contracts.Dto;
import org.investmentsimspring.models.assets.AssetDto;

public class LiabilityDto implements Dto {

    public long id;
    public String description;
    public double expense;
    public AssetDto assetDto;

}

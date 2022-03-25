package org.investmentsimspring.domain.liabilities;

import org.investmentsimspring.domain.assets.AssetDto;
import org.investmentsimspring.domain.contracts.Dto;

public class LiabilityDto implements Dto {

    public long id;
    public String description;
    public double expense;
    public AssetDto assetDto;

}

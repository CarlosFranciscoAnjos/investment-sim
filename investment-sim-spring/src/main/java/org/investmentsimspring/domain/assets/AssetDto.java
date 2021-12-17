package org.investmentsimspring.domain.assets;

import org.investmentsimspring.domain.contracts.Dto;
import org.investmentsimspring.domain.plans.PlanDto;

public class AssetDto implements Dto {

    public long id;
    public PlanDto plan;
    public String description;
    public double income;
    public double value;
    public double rate;

}

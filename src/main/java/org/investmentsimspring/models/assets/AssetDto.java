package org.investmentsimspring.models.assets;

import org.investmentsimspring.domain.contracts.Dto;
import org.investmentsimspring.models.simulations.SimulationDto;

public class AssetDto implements Dto {

    public long id;
    public SimulationDto simulationDto;
    public String description;
    public double income;
    public double value;
    public double rate;

}

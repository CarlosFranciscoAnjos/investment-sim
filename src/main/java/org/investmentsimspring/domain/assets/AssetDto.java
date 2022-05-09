package org.investmentsimspring.domain.assets;

import org.investmentsimspring.domain.containers.ContainerDto;
import org.investmentsimspring.domain.contracts.Dto;

public class AssetDto implements Dto {

    public long id;
    public ContainerDto containerDto;
    public String description;
    public double income;
    public double value;
    public double rate;

}

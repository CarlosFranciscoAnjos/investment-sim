package org.investmentsimspring.domain.simulations;

import org.investmentsimspring.domain.containers.ContainerDto;
import org.investmentsimspring.domain.contracts.Dto;
import org.investmentsimspring.domain.users.UserDto;

public class SimulationDto implements Dto {

    public long id;
    public UserDto user;
    public String description;
    public ContainerDto containerDto;
}

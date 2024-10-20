package org.investmentsimspring.models.simulations;

import org.investmentsimspring.domain.contracts.Dto;
import org.investmentsimspring.models.users.UserDto;

public class SimulationDto implements Dto {

    public long id;
    public UserDto user;
    public String description;
}

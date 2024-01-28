package org.investmentsimspring.infrastructure;

import org.investmentsimspring.domain.simulations.Simulation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SimulationsRepository extends JpaRepository<Simulation, Long> {

    List<Simulation> findAllByUserId(long id);
}

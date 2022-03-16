package org.investmentsimspring.infrastructure;

import org.investmentsimspring.domain.plans.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlansRepository extends JpaRepository<Plan, Long> {

    List<Plan> findAllByUserId(long id);
}

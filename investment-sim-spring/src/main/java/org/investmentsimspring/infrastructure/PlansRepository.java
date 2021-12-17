package org.investmentsimspring.infrastructure;

import org.investmentsimspring.domain.plans.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlansRepository extends JpaRepository<Plan, Long> {
}

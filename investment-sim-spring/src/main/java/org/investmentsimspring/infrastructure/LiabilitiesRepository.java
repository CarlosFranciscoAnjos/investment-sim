package org.investmentsimspring.infrastructure;

import org.investmentsimspring.domain.liabilities.Liability;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LiabilitiesRepository extends JpaRepository<Liability, Long> {
}

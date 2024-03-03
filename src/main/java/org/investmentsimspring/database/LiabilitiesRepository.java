package org.investmentsimspring.database;

import org.investmentsimspring.domain.liabilities.Liability;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LiabilitiesRepository extends JpaRepository<Liability, Long> {
}

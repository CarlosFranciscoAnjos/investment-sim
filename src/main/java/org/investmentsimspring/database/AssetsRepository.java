package org.investmentsimspring.database;

import org.investmentsimspring.domain.assets.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetsRepository extends JpaRepository<Asset, Long> {
}

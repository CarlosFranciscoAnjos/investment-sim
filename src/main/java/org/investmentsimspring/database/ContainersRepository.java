package org.investmentsimspring.database;

import org.investmentsimspring.domain.containers.Container;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContainersRepository extends JpaRepository<Container, Long> {

    List<Container> findAllByUserId(long id);
}

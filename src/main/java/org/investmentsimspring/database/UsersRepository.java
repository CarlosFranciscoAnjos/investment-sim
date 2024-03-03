package org.investmentsimspring.database;

import org.investmentsimspring.domain.users.Email;
import org.investmentsimspring.domain.users.User;
import org.investmentsimspring.domain.users.Username;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Long> {

    User findByUsername(Username username);

    User findByEmail(Email email);
}

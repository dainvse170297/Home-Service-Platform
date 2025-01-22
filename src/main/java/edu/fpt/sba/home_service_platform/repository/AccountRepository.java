package edu.fpt.sba.home_service_platform.repository;

import edu.fpt.sba.home_service_platform.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    Optional<Account> findByUsername(String username);
}

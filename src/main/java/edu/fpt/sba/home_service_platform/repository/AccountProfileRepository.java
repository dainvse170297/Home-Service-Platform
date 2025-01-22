package edu.fpt.sba.home_service_platform.repository;

import edu.fpt.sba.home_service_platform.entities.AccountProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountProfileRepository extends JpaRepository<AccountProfile, Integer> {

    Optional<AccountProfile> findByEmail(String email);

    Optional<AccountProfile> findByPhone(String phone);
}

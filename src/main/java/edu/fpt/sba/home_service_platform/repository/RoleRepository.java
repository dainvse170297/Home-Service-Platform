package edu.fpt.sba.home_service_platform.repository;

import edu.fpt.sba.home_service_platform.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}

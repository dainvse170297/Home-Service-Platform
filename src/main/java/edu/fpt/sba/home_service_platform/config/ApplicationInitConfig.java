package edu.fpt.sba.home_service_platform.config;

import edu.fpt.sba.home_service_platform.entities.Role;
import edu.fpt.sba.home_service_platform.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class ApplicationInitConfig {

    @Autowired
    private RoleRepository roleRepository;

    @Bean
    ApplicationRunner init(){
        return args -> {
            if(roleRepository.findAll().isEmpty()){
                Role roleAdmin = new Role();
                roleAdmin.setRoleName("ROLE_ADMIN");
                roleRepository.save(roleAdmin);
                Role roleCustomer = new Role();
                roleCustomer.setRoleName("ROLE_CUSTOMER");
                roleRepository.save(roleCustomer);
                Role roleHousekeeper = new Role();
                roleHousekeeper.setRoleName("ROLE_HOUSEKEEPER");
                roleRepository.save(roleHousekeeper);
                log.info("Role init");
            }
        };
    }

}

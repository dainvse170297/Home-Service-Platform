package edu.fpt.sba.home_service_platform.config.security.utils;

import edu.fpt.sba.home_service_platform.entities.Account;
import edu.fpt.sba.home_service_platform.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final AccountRepository accountRepository;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Invalid Username or Password"));
        if (!account.isActive()){
            throw new RuntimeException("Account is not active");
        }
        return UserDetailsImpl.build(account);
    }
}

package edu.fpt.sba.home_service_platform.services.Impl;

import edu.fpt.sba.home_service_platform.config.security.jwt.JwtUtils;
import edu.fpt.sba.home_service_platform.dto.request.AccountCreateRequest;
import edu.fpt.sba.home_service_platform.dto.response.AccountDTO;
import edu.fpt.sba.home_service_platform.entities.Account;
import edu.fpt.sba.home_service_platform.entities.AccountProfile;
import edu.fpt.sba.home_service_platform.entities.Role;
import edu.fpt.sba.home_service_platform.mapper.AccountMapper;
import edu.fpt.sba.home_service_platform.repository.AccountProfileRepository;
import edu.fpt.sba.home_service_platform.repository.AccountRepository;
import edu.fpt.sba.home_service_platform.repository.RoleRepository;
import edu.fpt.sba.home_service_platform.services.IAccountService;
import jakarta.mail.MessagingException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AccountService implements IAccountService {

    AccountRepository accountRepository;
    AccountProfileRepository accountProfileRepository;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;
    AccountMapper accountMapper;
    EmailUtils emailUtils;
    JwtUtils jwtUtils;

    @Override
    public AccountDTO registerUserAccount(AccountCreateRequest request) throws MessagingException {
        if(accountRepository.findByUsername(request.getUsername()).isPresent()) {
            log.error("Username already exists");
            throw new RuntimeException("Username already exists");
        }else if(accountProfileRepository.findByEmail(request.getEmail()).isPresent()) {
            log.error("Email already exists");
            throw new RuntimeException("Email already exists");
        }else if(accountProfileRepository.findByPhone(request.getPhone()).isPresent()) {
            log.error("Phone already exists");
            throw new RuntimeException("Phone already exists");
        }
        AccountProfile profile = new AccountProfile();
        profile.setFirstName(request.getFirstName());
        profile.setLastName(request.getLastName());
        profile.setEmail(request.getEmail());
        profile.setPhone(request.getPhone());
        profile.setAddress(request.getAddress());
        accountProfileRepository.save(profile);
        Account account = new Account();
        account.setUsername(request.getUsername());
        account.setPassword(passwordEncoder.encode(request.getPassword()));
        account.setAccountProfile(profile);
        account.setActive(false);
        Role role = roleRepository.findByRoleName("ROLE_CUSTOMER");
        account.setRole(role);
        emailUtils.sendEmail(
                request.getEmail(),
                emailUtils.subjectRegister(),
                emailUtils.bodyRegister(
                        request.getUsername(),
                        request.getFirstName() + " " + request.getLastName(),
                        request.getPhone(),
                        request.getAddress()
                        )
        );

        Account savedAccount = accountRepository.save(account);

        return accountMapper.toAccountDTO(savedAccount);
    }

    @Override
    public AccountDTO verifyAccount(String token) {
        String username = jwtUtils.getUserNameFromJwtToken(token);
        Date expDate = jwtUtils.getExpDateFromToken(token);
        if(!expDate.before(new Date())){
            Account account = accountRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("Account not found"));
            if(account == null){
                throw new RuntimeException("User not found");
            }
            account.setActive(true);
            Account savedAccount = accountRepository.save(account);

            return accountMapper.toAccountDTO(savedAccount);
        }else{
            throw new RuntimeException("Token expired");
        }
    }

    @Override
    public Account getAuthenticatedAccount() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName(); //get username from jwt token

        return accountRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Account not found"));

    }
}

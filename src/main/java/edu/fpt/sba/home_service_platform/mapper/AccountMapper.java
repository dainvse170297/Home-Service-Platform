package edu.fpt.sba.home_service_platform.mapper;

import edu.fpt.sba.home_service_platform.dto.response.AccountDTO;
import edu.fpt.sba.home_service_platform.entities.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public AccountDTO toAccountDTO(Account account) {
        return AccountDTO.builder()
                .username(account.getUsername())
                .firstName(account.getAccountProfile().getFirstName())
                .lastName(account.getAccountProfile().getLastName())
                .email(account.getAccountProfile().getEmail())
                .phone(account.getAccountProfile().getPhone())
                .address(account.getAccountProfile().getAddress())
                .role(account.getRole().getRoleName())
                .coverImage(account.getAccountProfile().getCoverImage())
                .avatar(account.getAccountProfile().getAvatar())
                .active(account.isActive())
                .build();
    }
}

package edu.fpt.sba.home_service_platform.controller;

import edu.fpt.sba.home_service_platform.dto.request.AccountCreateRequest;
import edu.fpt.sba.home_service_platform.dto.response.AccountDTO;
import edu.fpt.sba.home_service_platform.entities.Account;
import edu.fpt.sba.home_service_platform.services.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final IAccountService accountService;

    @RequestMapping("/register")
    public ResponseEntity<AccountDTO> registerUserAccount(@RequestBody AccountCreateRequest account) {
        return ResponseEntity.ok(accountService.registerUserAccount(account));
    }
}

package edu.fpt.sba.home_service_platform.controller;

import edu.fpt.sba.home_service_platform.dto.request.AccountCreateRequest;
import edu.fpt.sba.home_service_platform.dto.request.VerifyAccountRequest;
import edu.fpt.sba.home_service_platform.dto.response.AccountDTO;
import edu.fpt.sba.home_service_platform.entities.Account;
import edu.fpt.sba.home_service_platform.services.IAccountService;
import jakarta.mail.MessagingException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final IAccountService accountService;

    @PostMapping("/register")
    public ResponseEntity<AccountDTO> registerUserAccount(@RequestBody AccountCreateRequest account) throws MessagingException {
        return new ResponseEntity<>(accountService.registerUserAccount(account), HttpStatus.CREATED);
    }

    @PostMapping("/verify")
    public ResponseEntity<AccountDTO> verifyAccount(@NonNull @RequestBody VerifyAccountRequest request) {
        return ResponseEntity.ok(accountService.verifyAccount(request.getToken()));
    }

    @GetMapping("/p")
    public ResponseEntity<Account> getAuthenticatedAccount() {
        return ResponseEntity.ok(accountService.getAuthenticatedAccount());
    }
}

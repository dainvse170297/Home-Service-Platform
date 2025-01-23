package edu.fpt.sba.home_service_platform.services;

import edu.fpt.sba.home_service_platform.dto.request.AccountCreateRequest;
import edu.fpt.sba.home_service_platform.dto.response.AccountDTO;
import edu.fpt.sba.home_service_platform.entities.Account;
import jakarta.mail.MessagingException;

public interface IAccountService {

    AccountDTO registerUserAccount(AccountCreateRequest accountCreateRequest) throws MessagingException;

    AccountDTO verifyAccount(String token);
}

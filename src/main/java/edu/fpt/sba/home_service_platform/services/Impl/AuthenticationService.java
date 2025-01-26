package edu.fpt.sba.home_service_platform.services.Impl;

import edu.fpt.sba.home_service_platform.config.security.jwt.JwtUtils;
import edu.fpt.sba.home_service_platform.config.security.utils.UserDetailsImpl;
import edu.fpt.sba.home_service_platform.dto.request.AuthenticationRequest;
import edu.fpt.sba.home_service_platform.dto.request.RefreshTokenRequest;
import edu.fpt.sba.home_service_platform.dto.response.AuthenticationResponse;
import edu.fpt.sba.home_service_platform.entities.Account;
import edu.fpt.sba.home_service_platform.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final AccountRepository accountRepository;

    public AuthenticationResponse login(AuthenticationRequest request) {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            String token = jwtUtils.generateJwtToken(userDetails);
            String refreshToken = "refreshToken";

            return new AuthenticationResponse(token, refreshToken);
        } catch (UsernameNotFoundException e) {
            throw new RuntimeException("User not found");
        }catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public AuthenticationResponse refreshToken(RefreshTokenRequest request) {

        var token = request.getAccessToken();



        Date refreshableDate = new Date(
                jwtUtils.getExpDateFromToken(token)
                        .toInstant()
                        .plus(7, ChronoUnit.DAYS)
                        .toEpochMilli()
        ); // get the date when the token is expired plus 7 days

        if(refreshableDate.after(new Date())){
            String username = jwtUtils.getUserNameFromJwtToken(request.getAccessToken());
            String newToken = jwtUtils.generateTokenFromUsername(username);

            return new AuthenticationResponse(newToken, "refreshToken");
        }else {
            throw new RuntimeException("Token is not refreshable");
        }
    }
}

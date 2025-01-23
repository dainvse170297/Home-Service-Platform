package edu.fpt.sba.home_service_platform.services.Impl;

import edu.fpt.sba.home_service_platform.config.security.jwt.JwtUtils;
import edu.fpt.sba.home_service_platform.config.security.utils.UserDetailsImpl;
import edu.fpt.sba.home_service_platform.dto.request.AuthenticationRequest;
import edu.fpt.sba.home_service_platform.dto.response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public AuthenticationResponse login(AuthenticationRequest request) {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            String token = jwtUtils.generateJwtToken(userDetails);
//            String refreshToken = jwtUtils.generateRefreshToken(authentication);
            String refreshToken = "refreshToken";
            boolean isValid = jwtUtils.validateToken(token);

            return new AuthenticationResponse(token, refreshToken);
        } catch (UsernameNotFoundException e) {
            throw new RuntimeException("User not found");
        }catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}

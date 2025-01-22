package edu.fpt.sba.home_service_platform.config.security.jwt;

import edu.fpt.sba.home_service_platform.config.security.utils.UserDetailsImpl;
import edu.fpt.sba.home_service_platform.entities.Account;
import edu.fpt.sba.home_service_platform.repository.AccountRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

@Component
@Slf4j
public class JwtUtils {

    @Value("${homelet.app.jwtSecret}")
    private String jwtSecret;
    @Value("${homelet.app.jwtExpirationMs}")
    private int jwtExpirationMs;
    @Autowired
    private AccountRepository accountRepository;

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(jwtSecret));
    }

    public boolean validateToken(String jwt){
        try {
            Jwts.parserBuilder().setSigningKey(key()).build().parse(jwt);
            return true;
        }catch (MalformedJwtException e){
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }

    public String getUserNameFromJwtToken(String jwt){
        return Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(jwt).getBody().getSubject();
    }

    public String generateTokenFromUsername(String username){
        Account account = accountRepository.findByUsername(username).get();
        return Jwts.builder()
                .setSubject(username)
                .setIssuer("homelet")
                .claim("accountId", account.getId())
                .claim("profileId", account.getAccountProfile().getId())
                .claim("role", account.getRole().getRoleName())
                .signWith(key(), SignatureAlgorithm.HS256)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
                .setId(String.valueOf(UUID.randomUUID()))
                .compact();
    }

    public String generateJwtToken(UserDetailsImpl userPrincipal){
        return generateTokenFromUsername(userPrincipal.getUsername());
    }
}

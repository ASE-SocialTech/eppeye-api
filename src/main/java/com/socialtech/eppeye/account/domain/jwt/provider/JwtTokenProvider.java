package com.socialtech.eppeye.account.domain.jwt.provider;

import com.socialtech.eppeye.shared.util.Utilities;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.security.core.userdetails.User;
import java.util.Date;

@Slf4j
@Component
public class JwtTokenProvider {
    @Value("${app.jwt-secret}")
    private String secret;

    @Value("${app.jwt-expiration-min}")
    private long expiration;

    public String generateToken(Authentication authentication) {
        var authenticatedUser = (User) authentication.getPrincipal();

        var expiryDate = new Date(new Date().getTime() + expiration * 60_000);

        return Jwts.builder()
                .subject(authenticatedUser.getUsername())
                .issuedAt(new Date())
                .expiration(expiryDate)
                .signWith(Utilities.getKey(secret))
                .claim("roles", Utilities.getRoles(authenticatedUser))
                .compact();
    }

    public String getEmailFromToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(Utilities.getKey(secret))
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(Utilities.getKey(secret))
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            return true;
        } catch (SignatureException ex) {
            log.warn("Firma del token inválida");
        } catch (MalformedJwtException ex) {
            log.warn("Token inválido");
        } catch (ExpiredJwtException ex) {
            log.warn("Token expirado");
        } catch (UnsupportedJwtException ex) {
            log.warn("Token no soportado");
        } catch (IllegalArgumentException ex) {
            log.warn("Claims vacíos");
        }

        return false;
    }
}
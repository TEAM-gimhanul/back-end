package com.backend.gimhanul.global.security.jwt;

import com.backend.gimhanul.domain.user.domain.User;
import com.backend.gimhanul.domain.user.facade.UserFacade;
import com.backend.gimhanul.global.config.properties.JwtProperties;
import com.backend.gimhanul.global.exceptions.ServerErrorException;
import com.backend.gimhanul.global.security.jwt.exception.TokenBadRequestException;
import com.backend.gimhanul.global.security.jwt.exception.TokenExpireException;
import com.backend.gimhanul.global.security.jwt.exception.TokenForgedException;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.Enumeration;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;
    private final UserFacade userFacade;

    public String generateAccessToken(String email) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, getSecretKey())
                .setHeaderParam("typ", "JWT")
                .setSubject(email)
                .claim("type", "access")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExp() * 1000))
                .compact();
    }

    private Claims parseToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(getSecretKey())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw TokenExpireException.EXCEPTION;
        } catch (SignatureException | MalformedJwtException e) {
            throw TokenForgedException.EXCEPTION;
        } catch (IllegalArgumentException e) {
            throw TokenBadRequestException.EXCEPTION;
        } catch (Exception e) {
            throw ServerErrorException.EXCEPTION;
        }
    }

    public String extract(HttpServletRequest request) {
        Enumeration<String> headers = request.getHeaders(jwtProperties.getHeader());

        while (headers.hasMoreElements()) {
            String value = headers.nextElement();
            if (value.toLowerCase().startsWith(jwtProperties.getPrefix().toLowerCase())) {
                return value.substring(jwtProperties.getPrefix().length()).trim();
            }
        }

        throw TokenBadRequestException.EXCEPTION;
    }

    public User validate(String token) {
        return userFacade.findUserById(parseToken(token).getSubject());
    }

    private String getSecretKey() {
        return Base64.getEncoder().encodeToString(jwtProperties.getSecret().getBytes());
    }
}

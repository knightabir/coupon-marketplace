package com.marketplace.couponMarketplace.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    // Secret key for signing JWT tokens
    private String SECRET_KEY = "TaK+HaV^uvCHEFsEVfypW#7g9^k*Z8$V";
    private static final Logger log = LoggerFactory.getLogger(JwtUtil.class);

    /**
     * Retrieves the signing key for JWT operations.
     *
     * @return a SecretKey used for signing the JWT.
     */
    private SecretKey getSigningKey() {
        log.info("Retrieving signing key");
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    /**
     * Extracts username from the JWT token.
     *
     * @param token JWT token from which the username is to be extracted.
     * @return the username contained in the token.
     */
    public String extractUsername(String token) {
        log.info("Extracting username from token");
        Claims claims = extractAllClaims(token);
        return claims.getSubject();
    }

    /**
     * Extracts expiration date from the JWT token.
     *
     * @param token JWT token from which the expiration date is to be extracted.
     * @return the expiration date of the token.
     */
    public Date extractExpiration(String token) {
        log.info("Extracting expiration date from token");
        Claims claims = extractAllClaims(token);
        return claims.getExpiration();
    }

    /**
     * Extracts all claims from the JWT token.
     *
     * @param token JWT token from which claims are to be extracted.
     * @return Claims object containing all claims from the token.
     */
    public Claims extractAllClaims(String token) {
        log.info("Extracting all claims from token");
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * Checks if the JWT token is expired.
     *
     * @param token JWT token to be checked.
     * @return true if the token is expired, false otherwise.
     */
    private boolean isTokenExpired(String token) {
        log.info("Checking if token is expired");
        Date expiration = extractExpiration(token);
        return expiration.before(new Date());
    }

    /**
     * Generates a new JWT token for the given username.
     *
     * @param username the username for which the token is to be generated.
     * @return a new JWT token as a String.
     */
    public String generateToken(String username) {
        log.info("Generating token for username: {}", username);
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    /**
     * Creates a JWT token with specified claims and subject.
     *
     * @param claims a map of claims to be included in the token.
     * @param subject the subject for which the token is to be created.
     * @return a JWT token as a String.
     */
    private String createToken(Map<String, Object> claims, String subject) {
        log.info("Creating token for subject: {}", subject);
        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .header().empty().add("type", "JWT")
                .and()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(getSigningKey())
                .compact();
    }

    /**
     * Validates the JWT token.
     *
     * @param token JWT token to be validated.
     * @return true if the token is valid, false otherwise.
     */
    public Boolean validateToken(String token) {
        log.info("Validating token");
        return !isTokenExpired(token);
    }
}
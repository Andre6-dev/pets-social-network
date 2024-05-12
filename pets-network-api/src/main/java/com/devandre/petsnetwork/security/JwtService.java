package com.devandre.petsnetwork.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Service class for handling JWT (JSON Web Token) operations.
 */
@Service
public class JwtService {

    @Value("${application.security.jwt.secret-key}")
    private String secretKey;  // Secret key for JWT signing and verification

    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;  // Expiration time for the JWT

    /**
     * Extracts the username from the JWT.
     *
     * @param token JWT from which the username is to be extracted
     * @return the username from the JWT
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extracts a claim from the JWT.
     *
     * @param token JWT from which the claim is to be extracted
     * @param claimsResolver function to resolve the claim from the JWT claims
     * @return the claim from the JWT
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Generates a JWT for a user.
     *
     * @param userDetails the user for which the JWT is to be generated
     * @return the generated JWT
     */
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    /**
     * Generates a JWT for a user with additional claims.
     *
     * @param extraClaims additional claims to be included in the JWT
     * @param userDetails the user for which the JWT is to be generated
     * @return the generated JWT
     */
    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
        return buildToken(extraClaims, userDetails, jwtExpiration);
    }

    /**
     * Builds a JWT with the provided claims, user details and expiration time.
     *
     * @param extraClaims additional claims to be included in the JWT
     * @param userDetails the user for which the JWT is to be generated
     * @param expiration the expiration time for the JWT
     * @return the built JWT
     */
    private String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            long expiration
    ) {
        // Extract authorities from UserDetails
        var authorities = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .claim("authorities", authorities)
                .signWith(getSignInKey())
                .compact();
    }

    /**
     * Checks if a JWT is valid for a user.
     *
     * @param token JWT to be checked
     * @param userDetails the user for which the JWT is to be checked
     * @return true if the JWT is valid for the user, false otherwise
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Checks if a JWT is expired.
     *
     * @param token JWT to be checked
     * @return true if the JWT is expired, false otherwise
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Extracts the expiration date from a JWT.
     *
     * @param token JWT from which the expiration date is to be extracted
     * @return the expiration date from the JWT
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extracts all claims from a JWT.
     *
     * @param token JWT from which the claims are to be extracted
     * @return the claims from the JWT
     */
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Generates a signing key for JWT from the secret key.
     *
     * @return the generated signing key
     */
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

package com.bbhub.jwt.token.manager.services;

import com.bbhub.jwt.token.manager.interfaces.ITokenManager;
import com.bbhub.jwt.token.manager.models.RefreshTokenModel;
import com.bbhub.jwt.token.manager.models.TokenOptionModel;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
public class TokenManager  implements ITokenManager {
    private final TokenOptionModel options;

    public TokenManager(TokenOptionModel options) {
        this.options = options;
    }

    @Override
    public String generateAccessToken(List<Map.Entry<String, String>> claims, boolean isTwoFactor) {
        if (claims == null || claims.isEmpty()) {
            throw new IllegalArgumentException("Claims cannot be null or empty.");
        }

        if (options.getSecretKey() == null || options.getSecretKey().isEmpty()) {
            throw new IllegalArgumentException("Secret key is not configured.");
        }

        SecretKey secretKey = Keys.hmacShaKeyFor(options.getSecretKey().getBytes(StandardCharsets.UTF_8));

        long expirationTimeMillis = isTwoFactor
                ? 10 * 60 * 1000
                : options.getAccessTokenExpirationMinutes() * 60 * 1000;

        return Jwts.builder()
                .setClaims(Map.of("claims", claims))
                .setIssuer(options.getIssuer())
                .setAudience(options.getAudience())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTimeMillis))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public RefreshTokenModel generateRefreshToken() {
        if(options.getRefreshTokenExpirationDays() <= 0) {
            throw new IllegalArgumentException("Refresh token expiration days must be greater than zero.");
        }

        String publicToken = createRefreshToken();
        String privateToken = hashRefreshToken(publicToken);
        Date expiryTime = new Date(System.currentTimeMillis() + (options.getRefreshTokenExpirationDays() * 24L * 60 * 60 * 1000));

        return new RefreshTokenModel(publicToken, privateToken, expiryTime);
    }

    @Override
    public String generateIdToken(List<Map.Entry<String, String>> claims) {
        if (claims == null || claims.isEmpty()) {
            throw new IllegalArgumentException("Claims cannot be null or empty.");
        }

        if (options.getSecretKey() == null || options.getSecretKey().isEmpty()) {
            throw new IllegalStateException("Secret Key is not configured.");
        }

        SecretKey key = Keys.hmacShaKeyFor(options.getSecretKey().getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .setClaims(Map.of("claims", claims))
                .setIssuer(options.getIssuer())
                .setAudience(options.getAudience())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + options.getIdTokenExpirationMinutes() * 60 * 1000))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public boolean validateRefreshToken(RefreshTokenModel refreshToken) {
        if (refreshToken == null) {
            throw new IllegalArgumentException("Refresh token cannot be null.");
        }

        String clientToken = hashRefreshToken(refreshToken.getPublicToken());
        return refreshToken.getPrivateToken().equals(clientToken) &&
                refreshToken.getExpiresAt().after(new Date());
    }

    // Private helpers
    private static String createRefreshToken() {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8));
    }

    private static String hashRefreshToken(String token) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(token.getBytes(StandardCharsets.UTF_8));
            return Base64.getUrlEncoder().withoutPadding().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Unable to hash refresh token.", e);
        }
    }
}

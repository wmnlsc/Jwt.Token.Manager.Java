package com.bbhub.jwt.token.manager.models;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "apitokenoptions")
public class TokenOptionModel {
    private String secretKey;
    private String issuer;
    private String audience;
    private long accessTokenExpirationMinutes;
    private long refreshTokenExpirationDays;
    private long idTokenExpirationMinutes;

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getAudience() {
        return audience;
    }

    public void setAudience(String audience) {
        this.audience = audience;
    }

    public long getAccessTokenExpirationMinutes() {
        return accessTokenExpirationMinutes;
    }

    public void setAccessTokenExpirationMinutes(long accessTokenExpirationMinutes) {
        this.accessTokenExpirationMinutes = accessTokenExpirationMinutes;
    }

    public long getRefreshTokenExpirationDays() {
        return refreshTokenExpirationDays;
    }

    public void setRefreshTokenExpirationDays(long refreshTokenExpirationDays) {
        this.refreshTokenExpirationDays = refreshTokenExpirationDays;
    }

    public long getIdTokenExpirationMinutes() {
        return idTokenExpirationMinutes;
    }

    public void setIdTokenExpirationMinutes(long idTokenExpirationMinutes) {
        this.idTokenExpirationMinutes = idTokenExpirationMinutes;
    }
}

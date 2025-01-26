package com.bbhub.jwt.token.manager.models;

import java.time.Instant;
import java.util.Date;

public class RefreshTokenModel {
    private String publicToken;
    private String privateToken;
    private Date expiresAt;

    public RefreshTokenModel(String publicToken, String privateToken, Date expiresAt) {
        this.publicToken = publicToken;
        this.privateToken = privateToken;
        this.expiresAt = expiresAt;
    }

    public String getPublicToken() {
        return publicToken;
    }

    public String getPrivateToken() {
        return privateToken;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public void setPublicToken(String publicToken) {
        this.publicToken = publicToken;
    }

    public void setPrivateToken(String privateToken) {
        this.privateToken = privateToken;
    }

    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
    }
}

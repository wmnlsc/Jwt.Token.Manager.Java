package com.bbhub.jwt.token.manager.models;

public class TokenResponseModel {
    private final String userId;
    private final String accessToken;
    private final String refreshToken;
    private final String idToken;
    private final boolean isMfaToken;

    public TokenResponseModel(String userId, String accessToken, String refreshToken, String idToken, boolean isMfaToken) {
        if (userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("UserId cannot be null or empty.");
        }

        if (accessToken == null || accessToken.isEmpty()) {
            throw new IllegalArgumentException("AccessToken cannot be null or empty.");
        }

        this.userId = userId;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.idToken = idToken;
        this.isMfaToken = isMfaToken;
    }

    public String getUserId() {
        return userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getIdToken() {
        return idToken;
    }

    public boolean isMfaToken() {
        return isMfaToken;
    }
}

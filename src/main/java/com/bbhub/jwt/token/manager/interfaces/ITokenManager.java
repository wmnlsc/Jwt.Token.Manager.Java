package com.bbhub.jwt.token.manager.interfaces;

import com.bbhub.jwt.token.manager.models.RefreshTokenModel;

import java.util.List;
import java.util.Map;

public interface ITokenManager {
    String generateAccessToken(List<Map.Entry<String, String>> claims, boolean isTwoFactor);
    RefreshTokenModel generateRefreshToken();
    String generateIdToken(List<Map.Entry<String, String>> claims);
    boolean validateRefreshToken(RefreshTokenModel refreshToken);
}

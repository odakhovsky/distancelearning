package com.distancelearning.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.distancelearning.api.util.ApiUtil;
import com.distancelearning.api.util.StringJoiner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by v.odahovskiy on 01.02.2016.
 */
public class AccessToken {
    public static final String ACCESS_TOKEN = "access_token";
    public static final String EXPIRES_IN = "expires_in";
    public static final String USER_ID = "user_id";
    public static final String EMAIL = "email";

    /**
     * String token for use in request parameters
     */
    public String accessToken = null;
    /**
     * Time when token expires
     */
    public int expiresIn = 0;
    /**
     * Current user id for this token
     */
    public String userId = null;

    /**
     * User email
     */
    public String email = null;

    /**
     * Indicates time of token creation
     */
    public long created = 0;

    /**
     * Save token into specified file
     *
     * @param filePath path to file with saved token
     */
    public void saveTokenToFile(String filePath) {
        ApiUtil.stringToFile(filePath, serialize());
    }

    /**
     * Save token into shared preferences with key
     *
     * @param ctx      Context for preferences
     * @param tokenKey Key for saving settings
     */
    public void saveTokenToSharedPreferences(Context ctx, String tokenKey) {
        if (ctx == null) {
            return;
        }
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        SharedPreferences.Editor edit = prefs.edit();
        edit.putString(tokenKey, serialize());
        edit.apply();
    }

    /**
     * Removes token from preferences with specified key
     * @param ctx      Context for preferences
     * @param tokenKey Key for saving settings
     */
    public static void removeTokenAtKey(Context ctx, String tokenKey) {
        if (ctx == null) {
            return;
        }
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        SharedPreferences.Editor edit = prefs.edit();
        edit.remove(tokenKey);
        edit.apply();
    }

    private AccessToken() {
    }

    /**
     * Serialize token to VKParameters
     * @return serialized VKParameters value
     */
    protected Map<String, String> tokenParams() {
        Map<String, String> params = new HashMap<>();
        params.put(ACCESS_TOKEN, accessToken);
        params.put(EXPIRES_IN, "" + expiresIn);
        params.put(USER_ID, userId);

        if (email != null) {
            params.put(EMAIL, email);
        }
        return params;
    }

    /**
     * Serialize token into string
     *
     * @return Serialized token string as query-string
     */
    protected String serialize() {
        return StringJoiner.joinParams(tokenParams());
    }

    /**
     * Retrieve token from key-value query string
     *
     * @param urlString string that contains URL-query part with token. E.g. access_token=eee&expires_in=0...
     * @return parsed token
     */
    public static AccessToken tokenFromUrlString(String urlString) {
        if (urlString == null)
            return null;
        Map<String, String> parameters = ApiUtil.explodeQueryString(urlString);

        return tokenFromParameters(parameters);
    }

    /**
     * Retrieve token from key-value map
     *
     * @param parameters map that contains token info
     * @return Parsed token
     */
    public static AccessToken tokenFromParameters(@Nullable Map<String, String> parameters) {
        if (parameters == null || parameters.size() == 0) {
            return null;
        }
        AccessToken token = new AccessToken();
        try {
            token.accessToken = parameters.get(ACCESS_TOKEN);
            token.userId = parameters.get(USER_ID);
            token.email = parameters.get(EMAIL);
            if (parameters.get(EXPIRES_IN) != null) { token.expiresIn = Integer.parseInt(parameters.get(EXPIRES_IN)); }

            return token.accessToken != null ? token : null;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Retrieves token from shared preferences
     *
     * @param ctx Context for preferences
     * @param key Key for retrieve token
     * @return Previously saved token or null
     */
    public static AccessToken tokenFromSharedPreferences(Context ctx, String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        return tokenFromUrlString(prefs.getString(key, null));
    }

    /**
     * Retrieve token from file. Token must be saved into file with saveTokenToFile method
     *
     * @param filePath path to file with saved token
     * @return Previously saved token or null
     */
    public static AccessToken tokenFromFile(String filePath) {
        try {
            String data = ApiUtil.fileToString(filePath);
            return tokenFromUrlString(data);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Checks expiration time of token and returns result.
     *
     * @return true if token has expired, false otherwise.
     */
    public boolean isExpired() {
        return expiresIn > 0 && expiresIn * 1000 + created < System.currentTimeMillis();
    }

    private static final String VK_SDK_ACCESS_TOKEN_PREF_KEY = "VK_SDK_ACCESS_TOKEN_PLEASE_DONT_TOUCH";

    private volatile static AccessToken sCurrentToken;

    /**
     * @return Returns shared instance of current access token
     */
    public static AccessToken currentToken() {
        if (sCurrentToken == null) {
            synchronized (AccessToken.class) {
                if (sCurrentToken == null) {
                    sCurrentToken = AccessToken.tokenFromSharedPreferences(UIHelper.getApplicationContext(), VK_SDK_ACCESS_TOKEN_PREF_KEY);
                }
            }
        }
        return sCurrentToken;
    }

    /**
     * Replaces token with new token, and saves it to shared preferences of application
     * @param newToken New access token to set. If null, removes old token from preferences
     * @return old value of access token
     */
    static AccessToken replaceToken(@NonNull Context ctx, @Nullable AccessToken newToken) {
        AccessToken oldToken = sCurrentToken;
        sCurrentToken = newToken;
        if (sCurrentToken != null) {
            sCurrentToken.save();
        } else {
            removeTokenAtKey(ctx, VK_SDK_ACCESS_TOKEN_PREF_KEY);
        }
        return oldToken;
    }

    /**
     * Saves this token into application shared preferences
     */
    public void save() {
        saveTokenToSharedPreferences(UIHelper.getApplicationContext(), VK_SDK_ACCESS_TOKEN_PREF_KEY);
    }


    /**
     * Creates copy of current token, with params from passed token
     * @param token Usually this is partly filled access token, made after validation
     * @return New access token with updated fields
     */
    public AccessToken copyWithToken(@NonNull AccessToken token) {
        Map<String, String> newTokenParams = tokenParams();
        newTokenParams.putAll(token.tokenParams());
        return AccessToken.tokenFromParameters(newTokenParams);
    }


}

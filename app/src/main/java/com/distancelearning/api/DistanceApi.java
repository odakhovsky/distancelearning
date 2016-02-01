package com.distancelearning.api;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Log;

import com.distancelearning.api.model.response.SimpleResponseBody;
import com.distancelearning.api.model.response.UserResponseBody;
import com.distancelearning.api.model.user.User;
import com.distancelearning.api.model.user.UserLogin;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class DistanceApi {

    private static volatile DistanceApi instance;
    private static Handler handler = new Handler(Looper.getMainLooper());

    private Retrofit retrofit;
    private static UserApi userApi;
    private static User currentUser;

    private static final List<AccessTokenTracker> sTokenListeners = new CopyOnWriteArrayList<>();


    public void auth(final UserLogin userLogin) {
                getUserApi().auth(userLogin, new ResponseCallback<UserResponseBody>() {
                    @Override
                    public void onSuccess(final UserResponseBody response) {
                        currentUser = response.getUser();

                        AccessToken newToken = AccessToken.tokenFromParameters(new HashMap<String, String>(){
                            {
                                put(AccessToken.ACCESS_TOKEN, response.getToken());
                                put(AccessToken.EMAIL, currentUser.getEmail());
                                put(AccessToken.USER_ID, currentUser.getId());
                            }
                        });
                        AccessToken.replaceToken(UIHelper.getApplicationContext(), newToken);
                        notifyTokenChanged(AccessToken.currentToken(), newToken);
                    }

                    @Override
                    public void onFailure(SimpleResponseBody response) {
                        Log.d("MainActivity", response.getMessage());
                    }
                });

    }

    public static DistanceApi getApi() throws RuntimeException {
        if (null == instance) {
            throw new RuntimeException("Must initialized in Application");
        }

        return instance;
    }

    public static void initialize(Context context) {
        if (!(context instanceof Application)) {
            if (context == null) {
                throw new NullPointerException("Application context cannot be null");
            } else {
                throw new RuntimeException("DistanceApi.initialize(Context) must be call from Application#onCreate()");
            }
        } else {
            if (!hasInStack(Application.class, "onCreate")) {
                throw new RuntimeException("DistanceApi.initialize(Context) must be call from Application#onCreate()");
            }
        }
        if (null == instance) {
            instance = new DistanceApi(context);
        }
        wakeUpSession(context);
    }

    public static AccessToken getAccessToken() {
        return AccessToken.currentToken();
    }


    public static boolean wakeUpSession(@NonNull final Context context) {
        final Context appContext = context.getApplicationContext();
        UIHelper.setApplicationContext(appContext);

        AccessToken token = AccessToken.currentToken();

        currentUser = userApi.userByAccessToken(token);
        return false;
    }

    private static boolean hasInStack(@NonNull final Class<?> clazz, @NonNull final String method) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (int i = stackTrace.length - 2; i >= 0; --i) {
            StackTraceElement element = stackTrace[i];
            try {
                Class<?> aClass = Class.forName(element.getClassName());
                aClass.asSubclass(clazz);
                if (method.equals(element.getMethodName())) {
                    return true;
                }
            } catch (ClassNotFoundException | ClassCastException e) {
                // nothing
            }
        }
        return false;
    }

    static void addVKTokenTracker(AccessTokenTracker vkAccessTokenTracker) {
        sTokenListeners.add(vkAccessTokenTracker);
    }

    static void removeVKTokenTracker(AccessTokenTracker vkAccessTokenTracker) {
        sTokenListeners.remove(vkAccessTokenTracker);
    }

    static void notifyTokenChanged(final AccessToken oldToken, final AccessToken newToken) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                for (AccessTokenTracker listener : sTokenListeners) {
                    listener.onAccessTokenChanged(oldToken, newToken);
                }
            }
        });
    }

    private DistanceApi(Context context) {
        retrofit = new Retrofit.Builder()
                .baseUrl(DistanceApiConsts.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UIHelper.setApplicationContext(context);
    }

    public UserApi getUserApi() {
        if (null == userApi) {
            userApi = new UserApi(retrofit);
        }
        return userApi;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public static void logout() {
        AccessToken.replaceToken(UIHelper.getApplicationContext(), null);
    }

    public static boolean isLoggedIn() {
        AccessToken token = AccessToken.currentToken();
        return token != null && !token.isExpired();
    }
}

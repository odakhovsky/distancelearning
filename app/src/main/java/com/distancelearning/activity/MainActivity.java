package com.distancelearning.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.distancelearning.R;
import com.distancelearning.api.AccessToken;
import com.distancelearning.api.AccessTokenTracker;


public class MainActivity extends AppCompatActivity {

    AccessTokenTracker vkAccessTokenTracker = new AccessTokenTracker() {
        @Override
        public void onAccessTokenChanged(@Nullable AccessToken oldToken, @Nullable AccessToken newToken) {
            if (newToken == null) {
                Toast.makeText(getApplicationContext(), "AccessToken invalidated", Toast.LENGTH_LONG).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vkAccessTokenTracker.startTracking();
        /*DistanceApi.getApi().getUserApi().create(new NewUser("Vladimir", "Odahovskiy", "04/03/1994", "none", "gluk924@mail.ru", "123456", "123456")
                ,callback);*/

        //DistanceApi.getApi().auth(new UserLogin("gluk923@mail.ru", "123456"));
        //Log.d("MainActivity", DistanceApi.getApi().getCurrentUser().toString());

        //Log.d("MainActivity", AccessToken.currentToken().userId);

    }
}

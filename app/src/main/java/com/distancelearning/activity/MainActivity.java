package com.distancelearning.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.distancelearning.R;
import com.distancelearning.api.DistanceApi;
import com.distancelearning.api.ResponseCallback;
import com.distancelearning.api.model.response.UserResponseBody;
import com.distancelearning.api.model.response.SimpleResponseBody;
import com.distancelearning.api.model.user.User;
import com.distancelearning.api.model.user.UserLogin;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ResponseCallback<UserResponseBody> callback = new ResponseCallback<UserResponseBody>() {
            @Override
            public void onSuccess(UserResponseBody response) {
                Log.d("MainActivity", response.getUser().toString());
            }

            @Override
            public void onFailure(SimpleResponseBody response) {
                Log.d("MainActivity", response.getMessage());
            }
        };

        /*DistanceApi.getApi().getUserApi().create(new NewUser("Vladimir", "Odahovskiy", "04/03/1994", "none", "gluk924@mail.ru", "123456", "123456")
                ,callback);*/

        DistanceApi.getApi().getUserApi().auth(new UserLogin("gluk923@mail.ru", "123456"), callback);



    }
}

package com.example.invoices;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import com.example.invoices.AppPreference;
import com.example.invoices.LoginFragment;

public class MainActivity extends AppCompatActivity implements MyInterface {

    public static AppPreference appPreference;
    public static String c_date;

    FrameLayout container_layout;

    public static UserService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView message = findViewById(R.id.message);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.80/api/user/")
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        service = retrofit.create(UserService.class);

        container_layout = findViewById(R.id.fragment_container);
        appPreference = new AppPreference(this);

        //Log.e("created_at: ", c_date);

        if (container_layout != null){
            if (savedInstanceState != null){
                return;
            }

            //check login status from sharedPreference
            if (appPreference.getLoginStatus()){
                //when true
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fragment_container, new ProfileFragment())
                        .commit();
            } else {
                // when get false
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fragment_container, new LoginFragment())
                        .commit();
            }
        }
    }

    // overridden from MyInterface
    /*@Override
    public void register() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new RegistrationFragment())
                .addToBackStack(null)
                .commit();
    }*/
    @Override
    public void login(String message, Integer id, String username, String name, String surname, String email,String jwt) {
        appPreference.setDisplayMessage(message);
        appPreference.setDisplayId(id);
        appPreference.setDisplayUsername(username);
        appPreference.setDisplayName(name);
        appPreference.setDisplaySurname(surname);
        appPreference.setDisplayEmail(email);
        appPreference.setDisplayJwt(jwt);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new ProfileFragment())
                .commit();
    }
    @Override
    public void logout() {
        appPreference.setLoginStatus(false);
        appPreference.setDisplayMessage("Message");
        appPreference.setDisplayJwt("jwt");

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new LoginFragment())
                .commit();
    }
}
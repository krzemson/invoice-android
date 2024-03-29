package com.example.invoices;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.example.invoices.MainActivity;
import com.example.invoices.MyInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private MyInterface loginFromActivityListener;
    private TextView registerTV;

    private EditText emailInput, passwordInput;
    private Button loginBtn;

    public LoginFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_login, container, false);

        // for login
        emailInput = view.findViewById(R.id.emailInput);
        passwordInput = view.findViewById(R.id.passwordInput);
        loginBtn = view.findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });

        registerTV = view.findViewById(R.id.registerTV);
        registerTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginFromActivityListener.register();
            }
        });
        return view;
    } //ending onCreateView

    private void loginUser() {
        String Email = emailInput.getText().toString();
        String Password = passwordInput.getText().toString();

        if (TextUtils.isEmpty(Email)){
            MainActivity.appPreference.showToast("Nazwa użytkownika jest wymagana");
        }  else if (TextUtils.isEmpty(Password)){
            MainActivity.appPreference.showToast("Hasło jest wymagane");
        } else if (Password.length() < 6){
            MainActivity.appPreference.showToast("Hasło musi zawierać więcej niż 6 znaków");
        } else {
            Map<String, String> params = new HashMap<>();
            params.put("username", Email);
            params.put("password", Password);

            Call<User> call = MainActivity.service.login(params);
//calling the api
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    //displaying the message from the response as toast

                    if (response.code() == 200){
                        MainActivity.appPreference.setLoginStatus(true); // set login status in sharedPreference
                        loginFromActivityListener.login(
                                response.body().getMessage(),
                                response.body().getId(),
                                response.body().getUsername(),
                                response.body().getName(),
                                response.body().getSurname(),
                                response.body().getEmail(),
                                response.body().getCompany(),
                                response.body().getAddress(),
                                response.body().getCity(),
                                response.body().getNip(),
                                response.body().getRegon(),
                                response.body().getJwt());
                    } else if (response.code() == 401){
                        MainActivity.appPreference.showToast("Błędne dane logowania");
                        emailInput.setText("");
                        passwordInput.setText("");
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    System.out.println(t.getMessage());
                }
            });
        }
    } //ending loginUser()

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        loginFromActivityListener = (MyInterface) activity;
    }

}

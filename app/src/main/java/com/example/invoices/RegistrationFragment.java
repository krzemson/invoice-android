package com.example.invoices;


import android.nfc.Tag;
import android.os.Bundle;

import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrationFragment extends Fragment {

    private EditText nameInput, emailInput, phoneInput, passwordInput;
    Button regBtn;

    public RegistrationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_registration, container, false);
        nameInput = view.findViewById(R.id.nameInput);
        emailInput = view.findViewById(R.id.emailInput);
        phoneInput = view.findViewById(R.id.phoneInput);
        passwordInput = view.findViewById(R.id.passwordInput);
        regBtn = view.findViewById(R.id.regBtn);
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
                Log.e("reg button", "clicked");
            }
        });
        return view;
    }

    public void registerUser() {
        String Username = nameInput.getText().toString();
        String Email = emailInput.getText().toString();
        String Phone = phoneInput.getText().toString();
        String Password = passwordInput.getText().toString();

        if (TextUtils.isEmpty(Username)){
            MainActivity.appPreference.showToast("Your name is required.");
        } else if (TextUtils.isEmpty(Email)){
            MainActivity.appPreference.showToast("Your email is required.");
        } else if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            MainActivity.appPreference.showToast("Invalid email");
        } else if (TextUtils.isEmpty(Password)){
            MainActivity.appPreference.showToast("Password required");
        } else if (Password.length() < 6){
            MainActivity.appPreference.showToast("Create a password at least 6 characters long.");
        }
        else {

            Map<String, String> params = new HashMap<>();
            params.put("username", Username);
            params.put("email", Email);
            params.put("phone", Phone);
            params.put("password", Password);

            Call<User> userCall = MainActivity.service.register(params);
            userCall.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.code() == 201){
                        Log.e("response", response.body().getEmail());
                        nameInput.setText("");
                        emailInput.setText("");
                        phoneInput.setText("");
                        passwordInput.setText("");
                        MainActivity.appPreference.showToast("Registered Successfully");
                    } else if (response.code() == 409){
                        MainActivity.appPreference.showToast("This email already exists");
                    } else if (response.code() == 503){
                        MainActivity.appPreference.showToast("Oops! something went wrong.");
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                }
            });
        }

    }

}

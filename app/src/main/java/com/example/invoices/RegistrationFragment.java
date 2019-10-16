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

    private EditText username, email, name, surname, password;
    Button regBtn;

    public RegistrationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_registration, container, false);
        username = view.findViewById(R.id.username);
        email = view.findViewById(R.id.email);
        name = view.findViewById(R.id.name);
        surname = view.findViewById(R.id.surname);
        password = view.findViewById(R.id.password);
        regBtn = view.findViewById(R.id.regBtn);
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
        return view;
    }

    public void registerUser() {
        String Username = username.getText().toString();
        String Email = email.getText().toString();
        String Name = name.getText().toString();
        String Surname = surname.getText().toString();
        String Password = password.getText().toString();

        if (TextUtils.isEmpty(Username)){
            MainActivity.appPreference.showToast("Login jest wymagany");
        } else if (TextUtils.isEmpty(Email)){
            MainActivity.appPreference.showToast("Email jest wymagany");
        } else if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            MainActivity.appPreference.showToast("Nieprawidłowy email");
        } else if (TextUtils.isEmpty(Password)){
            MainActivity.appPreference.showToast("Hasło jest wymagane");
        } else if (Password.length() < 6){
            MainActivity.appPreference.showToast("Hasło musi zawierać conajmniej 6 znaków");
        }
        else {

            Map<String, String> params = new HashMap<>();
            params.put("username", Username);
            params.put("password", Password);
            params.put("email", Email);
            params.put("name", Name);
            params.put("surname", Surname);

            Call<User> userCall = MainActivity.service.register(params);
            userCall.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.code() == 201){
                        username.setText("");
                        email.setText("");
                        name.setText("");
                        surname.setText("");
                        password.setText("");
                        MainActivity.appPreference.showToast("Pomyślnie zarejestrowano");
                    } else if (response.code() == 409){
                        MainActivity.appPreference.showToast("Email już istnieje");
                    } else if (response.code() == 503){
                        MainActivity.appPreference.showToast("Oops! Coś poszło nie tak");
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                }
            });
        }

    }

}

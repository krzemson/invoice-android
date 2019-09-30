package com.example.invoices;

import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.invoices.MainActivity;
import com.example.invoices.User;
import com.example.invoices.MyInterface;

import static com.example.invoices.MainActivity.c_date;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    private TextView name, email;
    private Button logoutBtn;

    LogoutInterface logoutListener;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);

        name = view.findViewById(R.id.name);
        String Name = "Witaj, " + MainActivity.appPreference.getDisplayName() + " " + MainActivity.appPreference.getDisplaySurname();
        name.setText(Name);

        email = view.findViewById(R.id.email);

        String Jwt = "Twój login to: " + MainActivity.appPreference.getDisplayUsername();
        email.setText(Jwt);

        logoutBtn = view.findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoutListener.logout();
            }
        });

        return view;
    } // ending onCreateView

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        logoutListener = (LogoutInterface) activity;

    }
}
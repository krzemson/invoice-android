package com.example.invoices.ui.account;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.invoices.AppPreference;
import com.example.invoices.LogoutInterface;
import com.example.invoices.MainActivity;
import com.example.invoices.R;

public class AccountFragment extends Fragment {

    private AccountViewModel accountViewModel;
    private Button logoutBtn;
    LogoutInterface logoutListener;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        accountViewModel =
                ViewModelProviders.of(this).get(AccountViewModel.class);
        View root = inflater.inflate(R.layout.fragment_account, container, false);
        final TextView textView = root.findViewById(R.id.text_share);
        accountViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        String username = MainActivity.appPreference.getDisplayUsername();

        EditText usernameView = root.findViewById(R.id.username);
        usernameView.setText(username);

        String name = MainActivity.appPreference.getDisplayName();

        EditText nameView = root.findViewById(R.id.name);
        nameView.setText(name);

        String surname = MainActivity.appPreference.getDisplaySurname();

        EditText surnameView = root.findViewById(R.id.surname);
        surnameView.setText(surname);

        String email = MainActivity.appPreference.getDisplayEmail();

        EditText emailView = root.findViewById(R.id.email);
        emailView.setText(email);

        String firma = MainActivity.appPreference.getDisplayCompany();

        EditText firmaView = root.findViewById(R.id.firma);
        firmaView.setText(firma);

        String ulica = MainActivity.appPreference.getDisplayAddress();

        EditText ulicaView = root.findViewById(R.id.ulica);
        ulicaView.setText(ulica);

        String miasto = MainActivity.appPreference.getDisplayCity();

        EditText miastoView = root.findViewById(R.id.miasto);
        miastoView.setText(miasto);

        String nip = MainActivity.appPreference.getDisplayNip();

        EditText nipView = root.findViewById(R.id.nip);
        nipView.setText(nip);

        String regon = MainActivity.appPreference.getDisplayRegon();

        EditText regonView = root.findViewById(R.id.regon);
        regonView.setText(regon);

        logoutBtn = root.findViewById(R.id.logoutBtn);

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoutListener.logout();
            }
        });
        return root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        logoutListener = (LogoutInterface) activity;
    }
}
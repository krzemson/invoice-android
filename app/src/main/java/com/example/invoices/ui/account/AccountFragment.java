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
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.invoices.AppPreference;
import com.example.invoices.LogoutInterface;
import com.example.invoices.MainActivity;
import com.example.invoices.R;
import com.example.invoices.User;
import com.example.invoices.ui.invoice.InvoiceFragment;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountFragment extends Fragment {

    private AccountViewModel accountViewModel;
    private Button logoutBtn;
    private Button saveBtn;
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

        final EditText nameView = root.findViewById(R.id.name);
        nameView.setText(name);

        String surname = MainActivity.appPreference.getDisplaySurname();

        final EditText surnameView = root.findViewById(R.id.surname);
        surnameView.setText(surname);

        String email = MainActivity.appPreference.getDisplayEmail();

        final EditText emailView = root.findViewById(R.id.email);
        emailView.setText(email);

        String firma = MainActivity.appPreference.getDisplayCompany();

        final EditText firmaView = root.findViewById(R.id.firma);
        firmaView.setText(firma);

        String ulica = MainActivity.appPreference.getDisplayAddress();

        final EditText ulicaView = root.findViewById(R.id.ulica);
        ulicaView.setText(ulica);

        String miasto = MainActivity.appPreference.getDisplayCity();

        final EditText miastoView = root.findViewById(R.id.miasto);
        miastoView.setText(miasto);

        String nip = MainActivity.appPreference.getDisplayNip();

        final EditText nipView = root.findViewById(R.id.nip);
        nipView.setText(nip);

        String regon = MainActivity.appPreference.getDisplayRegon();

        final EditText regonView = root.findViewById(R.id.regon);
        regonView.setText(regon);

        saveBtn = root.findViewById(R.id.save);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> params = new HashMap<>();
                params.put("name", nameView.getText().toString());
                params.put("surname", surnameView.getText().toString());
                params.put("email", emailView.getText().toString());
                params.put("city", miastoView.getText().toString());
                params.put("address", ulicaView.getText().toString());
                params.put("company", firmaView.getText().toString());
                params.put("nip", nipView.getText().toString());
                params.put("regon", regonView.getText().toString());

                Call<User> userCall = MainActivity.service.user(MainActivity.appPreference.getDisplayJwt(), MainActivity.appPreference.getDisplayId(), params);
                userCall.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.code() == 200){

                            MainActivity.appPreference.setDisplayName(nameView.getText().toString());
                            MainActivity.appPreference.setDisplaySurname(surnameView.getText().toString());
                            MainActivity.appPreference.setDisplayEmail(emailView.getText().toString());
                            MainActivity.appPreference.setDisplayCity(miastoView.getText().toString());
                            MainActivity.appPreference.setDisplayAddress(ulicaView.getText().toString());
                            MainActivity.appPreference.setDisplayCompany(firmaView.getText().toString());
                            MainActivity.appPreference.setDisplayNip(nipView.getText().toString());
                            MainActivity.appPreference.setDisplayRegon(regonView.getText().toString());

                            FragmentTransaction ftr = getFragmentManager().beginTransaction();
                            ftr.detach(AccountFragment.this).attach(AccountFragment.this).commit();

                            MainActivity.appPreference.showToast("Pomyślnie edytowano !");
                        } else if (response.code() == 401){
                            MainActivity.appPreference.showToast("Token jest nieprawidłowy");
                        } else if (response.code() == 503){
                            MainActivity.appPreference.showToast("Oops! Coś poszło nie tak");
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                    }
                });
            }
        });

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
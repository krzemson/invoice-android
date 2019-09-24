package com.example.invoices.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.invoices.Invoice;
import com.example.invoices.Invoices;
import com.example.invoices.MainActivity;
import com.example.invoices.R;
import com.example.invoices.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.Wrapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        galleryViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });


        Map<String, String> params = new HashMap<>();
        params.put("jwt", MainActivity.appPreference.getDisplayJwt());

        Call<Invoice> call = MainActivity.service.invoices(params);

//calling the api
        call.enqueue(new Callback<Invoice>() {
            @Override
            public void onResponse(Call<Invoice> call, Response<Invoice> response) {
                //displaying the message from the response as toast
                if (response.code() == 200){

                    for (Invoices inv : response.body().getFaktury()){

                        System.out.println(inv.getNrFaktury());

                    }
                    
                    System.out.println(response.body().getFaktury());

                    MainActivity.appPreference.showToast("Granted");
                } else if (response.code() == 401){
                    MainActivity.appPreference.showToast("Access Denied");
                }
            }

            @Override
            public void onFailure(Call<Invoice> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });

        return root;
    }
}
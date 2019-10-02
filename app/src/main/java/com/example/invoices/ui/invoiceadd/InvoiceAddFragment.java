package com.example.invoices.ui.invoiceadd;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;

import com.example.invoices.Customer;
import com.example.invoices.Customers;
import com.example.invoices.Invoice;
import com.example.invoices.MainActivity;
import com.example.invoices.R;
import com.example.invoices.ui.invoice.InvoiceFragment;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InvoiceAddFragment extends Fragment {

    private InvoiceAddViewModel invoiceAddViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        invoiceAddViewModel =
                ViewModelProviders.of(this).get(InvoiceAddViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_invoiceadd, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        invoiceAddViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        Call<Customer> call = MainActivity.service.customers(MainActivity.appPreference.getDisplayJwt());

        call.enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                //displaying the message from the response as toast

                if (response.code() == 200){

                    final List<Customers> customers = response.body().getCustomers();

                    final Spinner dynamicSpinner = (Spinner) root.findViewById(R.id.dynamic_spinner);

                    ArrayList<String> items = new ArrayList<>();

                    for (Customers customer : customers){

                        items.add(customer.getCompany() + " - " + customer.getName() + " " + customer.getSurname());
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                            android.R.layout.simple_spinner_dropdown_item, items);

                    dynamicSpinner.setAdapter(adapter);


                    dynamicSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view,
                                                   int position, long id) {
                            Log.v("item", (String) parent.getItemAtPosition(position));

                            Button btn = root.findViewById(R.id.save);

                            btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    final TextInputEditText gross = root.findViewById(R.id.brutto);
                                    final Integer customer = customers.get(dynamicSpinner.getSelectedItemPosition()).getId();
                                    final Integer user = MainActivity.appPreference.getDisplayId();
                                    final TextInputEditText vat = root.findViewById(R.id.vat);
                                    final TextInputEditText netto = root.findViewById(R.id.netto);

                                    String Customer = customer.toString();
                                    String User = user.toString();
                                    String Gross = gross.getText().toString();
                                    String Vat = vat.getText().toString();
                                    String Netto = netto.getText().toString();

                                    Map<String, String> params = new HashMap<>();
                                    params.put("customer", Customer);
                                    params.put("user", User);
                                    params.put("gross", Gross);
                                    params.put("vat", Vat);
                                    params.put("netto", Netto);

                                    Call<Invoice> call = MainActivity.service.create(MainActivity.appPreference.getDisplayJwt(), params);

                                    System.out.println(call.request());

                                    call.enqueue(new Callback<Invoice>() {
                                        @Override
                                        public void onResponse(Call<Invoice> call, Response<Invoice> response) {
                                            //displaying the message from the response as toast

                                            if (response.code() == 201){

                                                NavHostFragment.findNavController(InvoiceAddFragment.this).navigate(R.id.nav_slideshow);

                                                MainActivity.appPreference.showToast("Faktura została dodana");

                                            } else if (response.code() == 503){
                                                MainActivity.appPreference.showToast("Nie można utworzyć faktury");
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<Invoice> call, Throwable t) {
                                            System.out.println(t.getMessage());
                                        }
                                    });
                                }
                            });
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            // TODO Auto-generated method stub
                        }

                    });

                } else if (response.code() == 401){
                    MainActivity.appPreference.showToast("Nie znaleziono klienta");
                }
            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });

        return root;
    }
}
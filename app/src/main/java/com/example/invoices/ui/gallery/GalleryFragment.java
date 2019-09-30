package com.example.invoices.ui.gallery;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;

import com.example.invoices.DashboardActivity;
import com.example.invoices.Invoice;
import com.example.invoices.Invoices;
import com.example.invoices.MainActivity;
import com.example.invoices.R;
import com.example.invoices.User;
import com.example.invoices.ui.home.HomeFragment;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.ContentHandler;
import java.net.Inet4Address;
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
        final View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        galleryViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });


        Call<Invoice> call = MainActivity.service.invoices(MainActivity.appPreference.getDisplayJwt());

//calling the api
        call.enqueue(new Callback<Invoice>() {
            @Override
            public void onResponse(Call<Invoice> call, Response<Invoice> response) {
                //displaying the message from the response as toast
                if (response.code() == 200){

                    TableLayout ll = (TableLayout) root.findViewById(R.id.main_table);

                    TableRow tr_head= new TableRow(getActivity());
                    // part1
                    tr_head.setLayoutParams(new TableRow.LayoutParams(
                            TableRow.LayoutParams.MATCH_PARENT,
                            TableRow.LayoutParams.WRAP_CONTENT));

                    TextView label_nrfaktury = new TextView(getActivity());

                    label_nrfaktury.setText("NrFV");
                    TableRow.LayoutParams params2 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
                    label_nrfaktury.setLayoutParams(params2);
                    label_nrfaktury.setGravity(Gravity.CENTER);
                    label_nrfaktury.setTypeface(Typeface.DEFAULT_BOLD);
                    label_nrfaktury.setPadding(5, 5, 5, 5);
                    tr_head.addView(label_nrfaktury);// add the column to the table row here

                    TextView label_klient = new TextView(getActivity());

                    label_klient.setText("Klient");
                    TableRow.LayoutParams params3 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
                    label_klient.setLayoutParams(params3);
                    label_klient.setGravity(Gravity.CENTER);
                    label_klient.setTypeface(Typeface.DEFAULT_BOLD);// part2
                    label_klient.setPadding(5, 5, 5, 5);
                    tr_head.addView(label_klient);// add the column to the table row here

                    TextView label_netto = new TextView(getActivity());    // part3

                    label_netto.setText("Netto");
                    TableRow.LayoutParams params4 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
                    label_netto.setLayoutParams(params4);
                    label_netto.setGravity(Gravity.CENTER);
                    label_netto.setTypeface(Typeface.DEFAULT_BOLD);// part2
                    label_netto.setPadding(5, 5, 5, 5);
                    tr_head.addView(label_netto);// add the column to the table row here

                    TextView label_vat = new TextView(getActivity());    // part3

                    label_vat.setText("Vat");
                    TableRow.LayoutParams params9 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
                    label_vat.setLayoutParams(params9);
                    label_vat.setGravity(Gravity.CENTER);
                    label_vat.setTypeface(Typeface.DEFAULT_BOLD);// part2
                    label_vat.setPadding(5, 5, 5, 5);
                    tr_head.addView(label_vat);// add the column to the table row here

                    TextView label_brutto = new TextView(getActivity());

                    label_brutto.setText("Brutto");
                    TableRow.LayoutParams params13 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
                    label_brutto.setLayoutParams(params13);
                    label_brutto.setGravity(Gravity.CENTER);
                    label_brutto.setTypeface(Typeface.DEFAULT_BOLD);// part2
                    label_brutto.setPadding(5, 5, 5, 5);
                    tr_head.addView(label_brutto);// add the column to the table row here

                    TextView label_action = new TextView(getActivity());

                    label_action.setText("Akcja");
                    TableRow.LayoutParams params14 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
                    label_action.setLayoutParams(params14);
                    label_action.setGravity(Gravity.CENTER);
                    label_action.setTypeface(Typeface.DEFAULT_BOLD);// part2
                    label_action.setPadding(5, 5, 5, 5);
                    tr_head.addView(label_action);// add the column to the table row here


                    ll.addView(tr_head, new TableLayout.LayoutParams(
                            TableRow.LayoutParams.MATCH_PARENT,
                            TableRow.LayoutParams.WRAP_CONTENT));

                    for (final Invoices inv : response.body().getFaktury()){

                        TextView textV = new TextView(getActivity());
                        TextView textV1 = new TextView(getActivity());
                        TextView textV2 = new TextView(getActivity());
                        TextView textV3 = new TextView(getActivity());
                        TextView textV4 = new TextView(getActivity());
                        TextView textV5 = new TextView(getActivity());
                        Button btn = new Button(getActivity());


                        final TableRow tr = new TableRow(getActivity());

                        //Create the tablerows

                        tr.setLayoutParams(new TableRow.LayoutParams(
                                TableRow.LayoutParams.MATCH_PARENT,
                                TableRow.LayoutParams.WRAP_CONTENT));


                            tr.setClickable(true);

                            tr.setOnClickListener(new View.OnClickListener() {
                                                      public void onClick(View v) {
                                                          AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());


                                                          LinearLayout layout = new LinearLayout(getActivity());
                                                          layout.setOrientation(LinearLayout.VERTICAL);

                                                          TextInputLayout tl_invnr = new TextInputLayout(getActivity());
                                                          TextInputLayout tl_gross = new TextInputLayout(getActivity());
                                                          TextInputLayout tl_customer = new TextInputLayout(getActivity());
                                                          TextInputLayout tl_vat = new TextInputLayout(getActivity());
                                                          TextInputLayout tl_netto = new TextInputLayout(getActivity());

                                                          TextInputEditText invnr = new TextInputEditText(getActivity());
                                                          TextInputEditText gross = new TextInputEditText(getActivity());
                                                          TextInputEditText customer = new TextInputEditText(getActivity());
                                                          TextInputEditText vat = new TextInputEditText(getActivity());
                                                          TextInputEditText netto = new TextInputEditText(getActivity());

                                                          invnr.setText(inv.getNrFaktury());
                                                          invnr.setSelection(invnr.getText().length());
                                                          tl_invnr.setHint("Numer Faktury");

                                                          tl_invnr.addView(invnr);
                                                          layout.addView(tl_invnr);

                                                          customer.setText(inv.getKlient());
                                                          customer.setSelection(invnr.getText().length());
                                                          tl_customer.setHint("Klient");

                                                          tl_customer.addView(customer);
                                                          layout.addView(tl_customer);

                                                          gross.setText(inv.getWartoscBrutto());
                                                          gross.setSelection(gross.getText().length());
                                                          tl_gross.setHint("Wartosc Brutto");

                                                          tl_gross.addView(gross);
                                                          layout.addView(tl_gross);

                                                          vat.setText(inv.getVat());
                                                          vat.setSelection(vat.getText().length());
                                                          tl_vat.setHint("Wartosc Vat");

                                                          tl_vat.addView(vat);
                                                          layout.addView(tl_vat);

                                                          netto.setText(inv.getWartoscNetto());
                                                          netto.setSelection(netto.getText().length());
                                                          tl_netto.setHint("Wartosc Netto");

                                                          tl_netto.addView(netto);
                                                          layout.addView(tl_netto);

                                                          alertDialog.setTitle("Edycja Faktury");
                                                          alertDialog.setMessage("Edytuj Fakturę");

                                                          alertDialog.setView(layout);

                                                          alertDialog.setPositiveButton("Edytuj",
                                                                  new DialogInterface.OnClickListener() {
                                                                      public void onClick(DialogInterface dialog, int which) {
                                                                          MainActivity.appPreference.showToast("Faktura zmieniona");
                                                                      }
                                                                  });
                                                          alertDialog.show();
                                                      }
                                                  });


                        // Here create the TextView dynamically

                        textV = new TextView(getActivity());
                        textV.setText(inv.getNrFaktury());
                        TableRow.LayoutParams params5 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
                        textV.setLayoutParams(params5);
                        textV.setGravity(Gravity.CENTER);

                        textV.setPadding(5, 20, 5, 20);
                        tr.addView(textV);

                        textV1 = new TextView(getActivity());
                        textV1.setText(inv.getKlient());
                        TableRow.LayoutParams params6 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
                        textV1.setLayoutParams(params6);
                        textV1.setGravity(Gravity.CENTER);

                        textV1.setPadding(5, 20, 5, 20);
                        tr.addView(textV1);

                        textV2 = new TextView(getActivity());
                        textV2.setText(inv.getWartoscNetto());
                        TableRow.LayoutParams params7 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
                        textV2.setLayoutParams(params7);
                        textV2.setGravity(Gravity.CENTER);

                        textV2.setPadding(5, 20, 5, 20);
                        tr.addView(textV2);

                        textV3 = new TextView(getActivity());
                        textV3.setText(inv.getVat());
                        TableRow.LayoutParams params8 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
                        textV3.setLayoutParams(params8);
                        textV3.setGravity(Gravity.CENTER);

                        textV3.setPadding(5, 20, 5, 20);
                        tr.addView(textV3);

                        textV4 = new TextView(getActivity());
                        textV4.setText(inv.getWartoscBrutto());
                        TableRow.LayoutParams params11 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
                        textV4.setLayoutParams(params11);
                        textV4.setGravity(Gravity.CENTER);

                        textV4.setPadding(5, 20, 5, 20);
                        tr.addView(textV4);

                        btn = new Button(getActivity());

                        btn.setText("Usuń");
                        TableRow.LayoutParams params12 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
                        btn.setLayoutParams(params12);
                        btn.getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
                        btn.setTextColor(Color.WHITE);
                        btn.setGravity(Gravity.CENTER);
                        btn.setPadding(5, 20, 5, 20);
                        tr.addView(btn);

                        ll.addView(tr, new TableLayout.LayoutParams(
                                TableRow.LayoutParams.MATCH_PARENT,
                                TableRow.LayoutParams.WRAP_CONTENT));

                        btn.setClickable(true);

                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());

                                final AlertDialog ad = alertDialog.create();

                                alertDialog.setTitle("Usunięcie faktury");
                                alertDialog.setMessage("Czy na pewno usunąć fakturę ?");

                                alertDialog.setPositiveButton("Usuń",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {

                                                Call<Invoice> call = MainActivity.service.delete(MainActivity.appPreference.getDisplayJwt(), inv.getId());

                                                call.enqueue(new Callback<Invoice>() {
                                                    @Override
                                                    public void onResponse(Call<Invoice> call, Response<Invoice> response) {
                                                        //displaying the message from the response as toast

                                                        if (response.code() == 200){

                                                            //NavHostFragment.findNavController(GalleryFragment.this).navigate(R.id.nav_share);

                                                            FragmentTransaction ftr = getFragmentManager().beginTransaction();
                                                            ftr.detach(GalleryFragment.this).attach(GalleryFragment.this).commit();

                                                            MainActivity.appPreference.showToast("Faktura usunieta");

                                                        } else if (response.code() == 401){
                                                            MainActivity.appPreference.showToast("Nie znaleziono faktury");
                                                        }
                                                    }

                                                    @Override
                                                    public void onFailure(Call<Invoice> call, Throwable t) {
                                                        System.out.println(t.getMessage());
                                                    }
                                                });

                                            }
                                        });

                                alertDialog.setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        ad.cancel();
                                    }
                                });

                                alertDialog.show();

                            }
                        });

                    }

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
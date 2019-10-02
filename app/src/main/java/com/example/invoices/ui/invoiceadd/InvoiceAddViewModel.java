package com.example.invoices.ui.invoiceadd;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class InvoiceAddViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public InvoiceAddViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Dodaj nową fakturę");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
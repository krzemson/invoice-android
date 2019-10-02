package com.example.invoices.ui.account;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.invoices.MainActivity;

public class AccountViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AccountViewModel() {
        mText = new MutableLiveData<>();
        String Name = "Witaj, " + MainActivity.appPreference.getDisplayName() + " " + MainActivity.appPreference.getDisplaySurname();
        mText.setValue(Name);
    }

    public LiveData<String> getText() {
        return mText;
    }
}
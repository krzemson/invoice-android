package com.example.invoices.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.invoices.MainActivity;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {

        String Name = "Witaj, " + MainActivity.appPreference.getDisplayName() + " " + MainActivity.appPreference.getDisplaySurname();

        mText = new MutableLiveData<>();
        mText.setValue(Name);
    }

    public LiveData<String> getText() {
        return mText;
    }
}
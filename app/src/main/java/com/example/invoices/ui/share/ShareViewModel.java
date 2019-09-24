package com.example.invoices.ui.share;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.invoices.MainActivity;

public class ShareViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ShareViewModel() {
        mText = new MutableLiveData<>();
        String Name = "Witaj, " + MainActivity.appPreference.getDisplayName() + " " + MainActivity.appPreference.getDisplaySurname();
        mText.setValue(Name);
    }

    public LiveData<String> getText() {
        return mText;
    }
}
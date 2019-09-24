package com.example.invoices;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Invoice {

    @SerializedName("faktury")
    @Expose
    private List<Invoices> faktury = null;

    public List<Invoices> getFaktury() {
        return faktury;
    }

    public void setFaktury(List<Invoices> faktury) {
        this.faktury = faktury;
    }

}

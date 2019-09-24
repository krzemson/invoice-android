package com.example.invoices;

import com.google.gson.annotations.SerializedName;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Invoices {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("nr_faktury")
    @Expose
    private String nrFaktury;
    @SerializedName("wartosc_brutto")
    @Expose
    private String wartoscBrutto;
    @SerializedName("wartosc_netto")
    @Expose
    private String wartoscNetto;
    @SerializedName("termin")
    @Expose
    private String termin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNrFaktury() {
        return nrFaktury;
    }

    public void setNrFaktury(String nrFaktury) {
        this.nrFaktury = nrFaktury;
    }

    public String getWartoscBrutto() {
        return wartoscBrutto;
    }

    public void setWartoscBrutto(String wartoscBrutto) {
        this.wartoscBrutto = wartoscBrutto;
    }

    public String getWartoscNetto() {
        return wartoscNetto;
    }

    public void setWartoscNetto(String wartoscNetto) {
        this.wartoscNetto = wartoscNetto;
    }

    public String getTermin() {
        return termin;
    }

    public void setTermin(String termin) {
        this.termin = termin;
    }

}

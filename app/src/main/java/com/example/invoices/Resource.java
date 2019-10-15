package com.example.invoices;

import com.google.gson.annotations.SerializedName;

public class Resource {
    @SerializedName("invoices")
    private Integer invoices;

    @SerializedName("invoicesP")
    private Integer invoicesP;

    @SerializedName("customers")
    private Integer customers;

    @SerializedName("suppliers")
    private Integer suppliers;

    @SerializedName("services")
    private Integer services;

    @SerializedName("servicesP")
    private Integer servicesP;

    public Integer getInvoices() {
        return invoices;
    }

    public Integer getInvoicesP() {
        return invoicesP;
    }

    public Integer getCustomers() { return customers;}

    public Integer getSuppliers() {
        return suppliers;
    }

    public Integer getServices() {
        return services;
    }

    public Integer getServicesP() {
        return servicesP;
    }

}
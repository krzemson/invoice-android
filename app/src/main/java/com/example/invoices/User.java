package com.example.invoices;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("message")
    private String message;

    @SerializedName("id")
    private Integer id;

    @SerializedName("username")
    private String username;

    @SerializedName("name")
    private String name;

    @SerializedName("surname")
    private String surname;

    @SerializedName("email")
    private String email;

    @SerializedName("company")
    private String company;

    @SerializedName("address")
    private String address;

    @SerializedName("city")
    private String city;

    @SerializedName("nip")
    private String nip;

    @SerializedName("regon")
    private String regon;

    @SerializedName("jwt")
    private String jwt;

    public String getMessage() {
        return message;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getNip() {
        return nip;
    }

    public String getRegon() {
        return regon;
    }

    public String getJwt() {
        return jwt;
    }

    @Override
    public String toString() {
        return "User{" +
                "message='" + message + '\'' +
                ", jwt='" + jwt + '\'' +
                '}';
    }
}

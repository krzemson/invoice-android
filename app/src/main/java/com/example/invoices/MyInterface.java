package com.example.invoices;

public interface MyInterface {

    // for login
    void register();
    void login(String message, Integer id, String username, String name, String surname, String email, String company,String address,String city,String nip,String regon,String jwt);
    void logout();
}

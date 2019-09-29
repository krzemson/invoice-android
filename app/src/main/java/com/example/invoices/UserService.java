package com.example.invoices;

import com.google.gson.JsonObject;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UserService {

    @Headers({
            "Content-Type:application/json"
    })
    @POST("user/login.php")
    Call<User> login(@Body Map<String, String> login);

    @POST("invoice/read.php")
    Call<Invoice> invoices(@Body Map<String, String> invoices);

    @DELETE("invoice/1")
    Call<Invoice> delete(@Body String invoices);

}

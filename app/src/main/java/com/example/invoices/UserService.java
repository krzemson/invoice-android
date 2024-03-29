package com.example.invoices;

import com.google.gson.JsonObject;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserService {
    @Headers({
            "Content-Type:application/json"
    })
    @POST("login")
    Call<User> login(@Body Map<String, String> login);

    @PUT("user/{id}")
    Call<User> user(@Header("Authorization") String authorization,@Path("id") int userId, @Body Map<String, String> user);

    @POST("invoices")
    Call<Invoice> create(@Header("Authorization") String authorization, @Body Map<String, String> invoice);

    @POST("register")
    Call<User> register(@Body Map<String, String> user);

    @GET("invoices")
    Call<Invoice> invoices(@Header("Authorization") String authorization);

    @GET("customers")
    Call<Customer> customers(@Header("Authorization") String authorization);

    @DELETE("invoices/{id}")
    Call<Invoice> delete(@Header("Authorization") String authorization,@Path("id") int invoiceId);

    @PUT("invoices/{id}")
    Call<Invoice> update(@Header("Authorization") String authorization,@Path("id") int invoiceId, @Body Map<String, String> invoice);

    @GET("resources")
    Call<Resource> resource(@Header("Authorization") String authorization);

}

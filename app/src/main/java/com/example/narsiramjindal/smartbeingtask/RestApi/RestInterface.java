package com.example.narsiramjindal.smartbeingtask.RestApi;

import com.example.narsiramjindal.smartbeingtask.model.UsersModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Narsi Ram Jindal on 2/24/2018.
 */

public interface RestInterface
{

    @GET("users")
    Call<List<UsersModel>> getUsers();

    @GET("users/{username}")
    Call<UsersModel> getSearchUser(@Path("username") String username);

  /*  @GET("getpaymentdetails/{payment_id}")
    Call<BatchPaymentDetails> getPatmentDetails(@Path("payment_id") int payment_id);*/
}

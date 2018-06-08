package com.example.narsiramjindal.smartbeingtask.RestApi;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Narsi Ram Jindal on 1/25/2018.
 */

public class RestApiClientCon {


    public static final String BASE_URL = "https://api.github.com/";

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {

        if (retrofit==null)
        {

            OkHttpClient okHttpClient = new OkHttpClient();

            retrofit = new Retrofit.Builder()

                    .baseUrl(BASE_URL)

                    .addConverterFactory(ScalarsConverterFactory.create())

                    .addConverterFactory(GsonConverterFactory.create())

                    .client(okHttpClient)

                    .build();
        }


        return retrofit;

    }

}

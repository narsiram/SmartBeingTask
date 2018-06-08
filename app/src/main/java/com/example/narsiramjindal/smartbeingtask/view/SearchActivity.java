package com.example.narsiramjindal.smartbeingtask.view;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.narsiramjindal.smartbeingtask.R;
import com.example.narsiramjindal.smartbeingtask.RestApi.RestApiClientCon;
import com.example.narsiramjindal.smartbeingtask.RestApi.RestInterface;
import com.example.narsiramjindal.smartbeingtask.model.UsersModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends Activity {

    private LinearLayout lytInflater = null ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        init();
        handleIntent(getIntent());

    }

    public void init()
    {
        lytInflater = findViewById(R.id.lyt_inflater);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);

            RestApiClientCon.getClient().create(RestInterface.class).getSearchUser(query).enqueue(new Callback<UsersModel>() {
                @Override
                public void onResponse(Call<UsersModel> call, Response<UsersModel> response) {
                    if(response.body() != null)
                    sendDataToView(response.body().getLogin() , response.body().getId() , response.body().getAvatarUrl());

                    else
                        Toast.makeText(SearchActivity.this, "Some Error Occured", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<UsersModel> call, Throwable t) {
                    Toast.makeText(SearchActivity.this, "api failure" , Toast.LENGTH_SHORT).show();

                }
            });
        }
        else
            Toast.makeText(this, "wrong search", Toast.LENGTH_SHORT).show();
    }

    private void sendDataToView(String username , int id , String url)
    {
        lytInflater.removeAllViews();

        View view = getLayoutInflater().inflate(R.layout.user_detail_card , lytInflater ,false);
        TextView txtUsername = (TextView) view.findViewById(R.id.txt_name);
        TextView txtId = (TextView) view.findViewById(R.id.txt_login_id);
        TextView txtUrl = (TextView) view.findViewById(R.id.txt_url);

        txtUsername.setText(username);
        txtId.setText(id + "");
        txtUrl.setText(url);

        lytInflater.addView(view);
    }

}

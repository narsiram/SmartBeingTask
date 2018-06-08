package com.example.narsiramjindal.smartbeingtask.view;

import android.app.SearchManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.narsiramjindal.smartbeingtask.R;
import com.example.narsiramjindal.smartbeingtask.RestApi.RestApiClientCon;
import com.example.narsiramjindal.smartbeingtask.RestApi.RestInterface;
import com.example.narsiramjindal.smartbeingtask.adapter.UserDetailAdapter;
import com.example.narsiramjindal.smartbeingtask.model.UsersModel;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView listRecycler = null ;
    private UserDetailAdapter cardAdapter = null ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init()
    {
        cardAdapter = new UserDetailAdapter(this) ;

        listRecycler = findViewById(R.id.recycler_view);

        listRecycler.setLayoutManager(new LinearLayoutManager(this ) );
        listRecycler.setAdapter(cardAdapter);

        getUserList();

    }

    @Override

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.search, menu);    // Associate searchable configuration with the SearchView

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;

    }

    public void getUserList()
    {
        RestApiClientCon.getClient().create(RestInterface.class).getUsers().enqueue(new Callback<List<UsersModel>>() {
            @Override
            public void onResponse(Call<List<UsersModel>> call, Response<List<UsersModel>> response) {
                if(response.body() != null)
                    sendDataToAdapter(response.body());

                else
                    Toast.makeText(MainActivity.this, "Some Error Occured", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<UsersModel>> call, Throwable t) {

                Toast.makeText(MainActivity.this, " Api Failure "+ t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void sendDataToAdapter(List<UsersModel> list)
    {
        cardAdapter.addDataToAdapter(list);
    }
}

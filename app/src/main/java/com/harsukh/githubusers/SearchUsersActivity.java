package com.harsukh.githubusers;

import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import android.widget.Toast;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.harsukh.githubusers.adapters.UsersListAdapter;
import com.harsukh.githubusers.models.GitUsersResponseModel;
import com.harsukh.githubusers.models.UsersListModel;
import com.harsukh.githubusers.network.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchUsersActivity extends AppCompatActivity {
    private SearchView searchView;
    private Context context;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_search);
        context = this;
        searchView = findViewById(R.id.search);
        searchView.setIconified(false);
        recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration decoration = new DividerItemDecoration(context, linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(decoration);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(query == null){
                   Toast.makeText(context, "Query is empty", Toast.LENGTH_LONG).show();
                }else{
                    getUsers(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void getUsers(String query) {
        Api.getClient().getUsers(query).enqueue(new Callback<GitUsersResponseModel>() {
            @Override
            public void onResponse(Call<GitUsersResponseModel> call, Response<GitUsersResponseModel> response) {
                if(response.body() != null) {
                    List<UsersListModel> usersList = response.body().getUsersListModels();
                    if(usersList != null) {
                        recyclerView.setAdapter(new UsersListAdapter(response.body().getUsersListModels()));
                    }
                    else{
                        Toast.makeText(context, "Empty response", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<GitUsersResponseModel> call, Throwable throwable) {
                Toast.makeText(context, throwable.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}

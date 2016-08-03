package com.example.yoo.retrofitex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;


import com.example.yoo.retrofitex.recyclerviewAdapter.MainRecyclerViewAdpater;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.mainActivity_recyclerview)
    RecyclerView mainActivity_recyclerview;

    MainRecyclerViewAdpater mainRecyclerViewAdpater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViews();
    }
    private void initViews() {
        final LinearLayoutManager manager = new LinearLayoutManager(getApplication(), LinearLayoutManager.VERTICAL, false);
        mainActivity_recyclerview.setLayoutManager(manager);

        //add item scroll last postion
        mainActivity_recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            boolean isLastItem;
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if(isLastItem && newState == RecyclerView.SCROLL_STATE_IDLE)
                {
                    List<Repo> additems = new ArrayList<Repo>();

                    for(int i = 0; i<10; i++) {
                        Repo additem = new Repo();
                        additem.setId(i);
                        additem.setName(String.valueOf(i));
                        additem.setFull_name(""+i);
                        additems.add(additem);
                    }

                    Log.e("hihi","asdf");
                    mainRecyclerViewAdpater.addAllItem(additems);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int totalItemCount = manager.getItemCount();
                int lastVisibleItemPosition = manager.findLastCompletelyVisibleItemPosition();
                if(totalItemCount > 0 && lastVisibleItemPosition != RecyclerView.NO_POSITION && (totalItemCount -1 <= lastVisibleItemPosition))
                {
                    isLastItem = true;
                }
                else
                {
                    isLastItem = false;
                }
            }
        });
        loadJSON();
    }
    private void loadJSON(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubService service = retrofit.create(GitHubService.class);
        Call<List<Repo>> repos = service.listRepos("ITSWYoo");
        repos.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                mainRecyclerViewAdpater = new MainRecyclerViewAdpater(getApplicationContext(), response.body());
                mainActivity_recyclerview.setAdapter(mainRecyclerViewAdpater);
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {

            }
        });
    }
}


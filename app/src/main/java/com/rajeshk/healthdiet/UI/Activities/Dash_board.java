package com.rajeshk.healthdiet.UI.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.WindowManager;

import com.google.gson.Gson;
import com.rajeshk.healthdiet.Model.Root;
import com.rajeshk.healthdiet.Presenters.Call_Backs.Fetch_Response;
import com.rajeshk.healthdiet.R;
import com.rajeshk.healthdiet.UI.Adapters.Dashboard_adapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Dash_board extends AppCompatActivity implements Fetch_Response {


    Root root;
    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    Integer[] items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dash_board);
        ButterKnife.bind(this);
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerview.setAdapter(new Dashboard_adapter(Dash_board.this,getResources().getStringArray(R.array.dashboard),items));
    }
    @Override
    public void getResponse(Root root,String  key) {
        Log.e("root is ","<><><"+root);
        if(null!=root){
            Gson gson = new Gson();
            Intent intent = new Intent(Dash_board.this,Videos_List.class);
            intent.putExtra("pojo",gson.toJson(root));
            String[] videos = getResources().getStringArray(R.array.videos_list);
            intent.putExtra("For_search",key);
            startActivity(intent);
        }
    }
}

package com.rajeshk.healthdiet.UI.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.gson.Gson;
import com.rajeshk.healthdiet.Model.Item;
import com.rajeshk.healthdiet.Model.Root;
import com.rajeshk.healthdiet.Presenters.Api_Client.Retrofit_Client;
import com.rajeshk.healthdiet.Presenters.Call_Backs.Fetch_Response;
import com.rajeshk.healthdiet.R;
import com.rajeshk.healthdiet.UI.Adapters.Videos_list_adapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Videos_List extends AppCompatActivity implements Fetch_Response{

    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.txt_title)
    TextView txt_title;
    List<Root> datalist = new ArrayList<>();
    boolean scrollflag,loading,flag = true;;
    Root temp_pojo;
    int spage =0;
    String  For_search;
    Videos_list_adapter adapter;
    ArrayList<Item> dataList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos__list);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ButterKnife.bind(this);
        recyclerview.setHasFixedSize(true);
        LinearLayoutManager horizontalLayoutManagaer_search
                = new LinearLayoutManager(Videos_List.this, LinearLayoutManager.VERTICAL, false);
        recyclerview.setLayoutManager(horizontalLayoutManagaer_search);
        Gson gson = new Gson();
        Bundle extras = getIntent().getExtras();
        For_search = extras.getString("For_search");
        Root root = gson.fromJson(extras.getString("pojo"), Root.class);
        temp_pojo = root;
        adapter = new Videos_list_adapter(Videos_List.this,root);
        recyclerview.setAdapter(adapter);

//        recyclerview.addOnScrollListener(new EndlessScrollListener(recyclerview));
    }

    @Override
    public void getResponse(Root root, String  position) {
        loading = false;
        if (spage == 5) {
            temp_pojo = root;
        } else {
            dataList = (ArrayList<Item>) temp_pojo.getItems();
            dataList.addAll(root.getItems());
            temp_pojo.setItems(dataList);
        }


        if (spage == 5) {

            recyclerview.setAdapter(adapter);

        } else {
            adapter.notifyDataSetChanged();
        }
        adapter.notifyDataSetChanged();


    }


    public class EndlessScrollListener extends RecyclerView.OnScrollListener {
        private RecyclerView listView;

        public EndlessScrollListener(RecyclerView listView) {
            this.listView = listView;
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        }

        @Override
        public void onScrollStateChanged(RecyclerView view, int scrollState) {
            Log.e("onScrollStateChanged", "<><><>");
            LinearLayoutManager layoutManager = ((LinearLayoutManager) view.getLayoutManager());
            int lastVisiblePosition = layoutManager.findLastVisibleItemPosition();
            int firstVisiblePosition = layoutManager.findFirstVisibleItemPosition();
            if (null != temp_pojo) {
                if (scrollState == 0
                        && lastVisiblePosition == listView.getAdapter().getItemCount() - 1) {
                    if (!loading) {
                        loading = true;
                        Log.e("coming to end less", "<><><>");
                        spage += 5;
                        Log.e("coming to end less and ", "<><page " + spage);
                        Retrofit_Client.getInstance().getResponse(For_search,spage,Videos_List.this);
                    }

                }
            }
        }
    }

}

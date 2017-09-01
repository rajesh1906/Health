package com.rajeshk.healthdiet.Presenters.Api_Client;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.rajeshk.healthdiet.Model.Root;
import com.rajeshk.healthdiet.Presenters.Call_Backs.Api;
import com.rajeshk.healthdiet.Presenters.Call_Backs.Fetch_Response;
import com.rajeshk.healthdiet.Presenters.End_Points.Config;
import com.rajeshk.healthdiet.Presenters.Helper.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ChRajeshKumar on 12-Apr-17.
 */

public class Retrofit_Client {
    public static Retrofit_Client client;
    public Root root ;
    ProgressDialog progressDialog;
    public Retrofit_Client(){

    }
    public static Retrofit_Client getInstance(){
        if(client == null){
            client = new Retrofit_Client();
        }
        return client;
    }

    public Retrofit getClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.getInstance().getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }


    public Root getResponse(final String key ,  int limit, final Context context){
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading.....");
        progressDialog.show();
        Api api = getClient().create(Api.class);
        Constants.getInstance().setSearchKey(key);

        Config.searchkey = key;
        Call<Root> serverCall = api.getData(key,Config.YOUTUBE_API_KEY,50,"");
        serverCall.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                progressDialog.dismiss();
                root= response.body();
                Log.e("response is ","<><>"+response.raw());
                Fetch_Response fetch_response = (Fetch_Response)context;
                fetch_response.getResponse(root,key);
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(context, "Opps!.. somthing error", Toast.LENGTH_SHORT).show();
//                return root;
            }
//            return root;
        });

        return root;
    }

}

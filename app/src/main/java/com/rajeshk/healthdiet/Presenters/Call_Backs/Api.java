package com.rajeshk.healthdiet.Presenters.Call_Backs;

import com.rajeshk.healthdiet.Model.Root;
import com.rajeshk.healthdiet.Presenters.End_Points.Config;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ChRajeshKumar on 13-Apr-17.
 */

public interface Api {
    String query = Config.search;







//    @GET("search?part=snippet&q="+""+"&type=video&key=" + Config.YOUTUBE_API_KEY + "&maxResults=" + 50 + "&pageToken=")
    @GET("search?part=snippet&type=video")
    Call<Root> getData(@Query("q") String Query, @Query("key") String key, @Query("maxResults") int limit, @Query("pageToken") String pageToken);
//    Call<Root> getData(@Query("q") String query);
}

package com.rajeshk.healthdiet.Presenters.End_Points;

import com.rajeshk.healthdiet.Presenters.Helper.Constants;

/**
 * Created by ChRajeshKumar on 9/13/2016.
 */
public class Config
{
    private Config() throws ClassNotFoundException {
    }

    public static final String YOUTUBE_API_KEY = "AIzaSyANC7YXqT-P7JYbsFShIl_6dhAEdkfYIEo";
    public static final String YOUTUBE_FEEDS_URL="https://www.googleapis.com";
    public static String   searchkey = Constants.getInstance().getSearchKey();
    public static final  String   search = searchkey;
    public static final String getSearchkey() {
        return searchkey;
    }

}

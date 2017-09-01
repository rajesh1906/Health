package com.rajeshk.healthdiet.Presenters.End_Points;

/**
 * Created by ChRajeshKumar on 13-Apr-17.
 */

public class EndPoints {
    public static String  searching;
    public static String nextpagetoken = "";




    public static String getBaseURL() {

//        searching="https://www.googleapis.com/youtube/v3/videos?chart=mostPopular&part=snippet&key="+Config.YOUTUBE_API_KEY+"&maxResults="+15+"&pageToken="+nextpagetoken;
        //searching="https://www.googleapis.com/youtube/v3/videos?chart=mostPopular&part=snippet&key=";

//        searching = "https://www.googleapis.com/youtube/v3/search?part=snippet&q=" + search_string + "&type=video&key=" + Config.YOUTUBE_API_KEY + "&maxResults=" + limit + "&pageToken=" + nextpagetoken;
        searching = "https://www.googleapis.com/youtube/v3/";
        return searching;

    }
}

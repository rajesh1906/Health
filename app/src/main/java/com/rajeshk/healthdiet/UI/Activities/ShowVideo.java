package com.rajeshk.healthdiet.UI.Activities;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.commit451.youtubeextractor.YouTubeExtractionResult;
import com.commit451.youtubeextractor.YouTubeExtractor;
import com.rajeshk.healthdiet.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Rajesh Kumar on 08-09-2017.
 */

public class ShowVideo extends AppCompatActivity
{
    VideoView videoView;
    private   String YOUTUBE_ID = "";
    Dialog dialog;

    private final YouTubeExtractor mExtractor = YouTubeExtractor.create();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.videoview);
        videoView = (VideoView)findViewById(R.id.video_view);

        Intent bundle =getIntent();
        if(null!=bundle)
        YOUTUBE_ID = bundle.getStringExtra("video_id");
        mExtractor.extract(YOUTUBE_ID).enqueue(mExtractionCallback);
        dialog = new Dialog (ShowVideo.this);
        dialog.requestWindowFeature (Window.FEATURE_NO_TITLE);
        dialog.setContentView (R.layout.custom_progress);
        dialog.getWindow ().setBackgroundDrawableResource (android.R.color.transparent);
        dialog.setCancelable(false);
        dialog.show ();
    }



    private Callback<YouTubeExtractionResult> mExtractionCallback = new Callback<YouTubeExtractionResult>() {
        @Override
        public void onResponse(Call<YouTubeExtractionResult> call, Response<YouTubeExtractionResult> response) {
            bindVideoResult(response.body());
        }

        @Override
        public void onFailure(Call<YouTubeExtractionResult> call, Throwable t) {
            onError(t);
        }
    };
    private void onError(Throwable t) {
        t.printStackTrace();
        Toast.makeText(ShowVideo.this, "It failed to extract. So sad", Toast.LENGTH_SHORT).show();
    }
    private void bindVideoResult(YouTubeExtractionResult result) {

//        Here you can get download url link
        Log.d("OnSuccess", "Got a result with the best url: " + result.getBestAvailableQualityVideoUri());
        try {
            // Start the MediaController
            MediaController mediacontroller = new MediaController(
                    ShowVideo.this);
            mediacontroller.setAnchorView(videoView);
            // Get the URL from String VideoURL
            videoView.setMediaController(mediacontroller);
            videoView.setVideoURI( result.getSd360VideoUri());

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        videoView.requestFocus();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            // Close the progress bar and play the video
            public void onPrepared(MediaPlayer mp) {
                dialog.dismiss();
                videoView.start();
            }
        });
    }
}

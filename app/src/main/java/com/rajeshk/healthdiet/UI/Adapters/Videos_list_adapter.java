package com.rajeshk.healthdiet.UI.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.rajeshk.healthdiet.Model.Root;
import com.rajeshk.healthdiet.R;
import com.rajeshk.healthdiet.UI.Activities.ShowVideo;

/**
 * Created by ChRajeshKumar on 15-Apr-17.
 */

public class Videos_list_adapter extends  RecyclerView.Adapter<Videos_list_adapter.MyViewHolder>  {
    private Context mContext;
    Root root;
    View itemView;

    public Videos_list_adapter(Context c, Root root) {
        mContext = c;
        this.root = root;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_title,txt_description;
        ImageView gridview_image,img_play;



        public MyViewHolder(View view) {
            super(view);
            txt_title = (TextView) view.findViewById(R.id.txt_title);
            txt_description = (TextView) view.findViewById(R.id.txt_description);
            gridview_image = (ImageView)view.findViewById(R.id.gridview_image);
            img_play = (ImageView)view.findViewById(R.id.img_play);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.channel_item, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.txt_title.setText(root.getItems().get(position).getSnippet().getTitle());
        holder.txt_description.setText(root.getItems().get(position).getSnippet().getDescription());
        Glide.with(mContext).load(root.getItems().get(position).getSnippet().getThumbnails().getHigh().getUrl())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.gridview_image);

        holder.img_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("video isd is ","<><>"+root.getItems().get(position).getId().getVideoId());
                Intent intent = new Intent(mContext, ShowVideo.class);
                intent.putExtra("video_id",root.getItems().get(position).getId().getVideoId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return root.getItems().size();
    }
}

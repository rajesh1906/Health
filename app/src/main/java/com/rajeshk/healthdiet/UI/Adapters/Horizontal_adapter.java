package com.rajeshk.healthdiet.UI.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rajeshk.healthdiet.Presenters.Api_Client.Retrofit_Client;
import com.rajeshk.healthdiet.Presenters.Helper.Constants;
import com.rajeshk.healthdiet.R;

import java.util.ArrayList;

/**
 * Created by ct on 01-09-2017.
 */

public class Horizontal_adapter extends RecyclerView.Adapter<Horizontal_adapter.ViewHolder> {

    Context context;
    final Integer[] Imageid;
    private LayoutInflater inflater;
    View itemView;
    Integer[] images = Constants.getInstance().getImages();

    public Horizontal_adapter(Context context,Integer[]  IMAGES) {
        this.context = context;
        this.Imageid=IMAGES;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.category_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,final int position) {
        holder.gridview_image.setBackgroundResource(images[position]);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit_Client.getInstance().getResponse(context.getResources().getStringArray(R.array.videos_list)[position],50,context);
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView gridview_text;
        ImageView gridview_image;
        RelativeLayout linearLayout;

        public ViewHolder(View view) {
            super(view);
            gridview_text = (TextView) view.findViewById(R.id.gridview_text);
            gridview_image = (ImageView) view.findViewById(R.id.gridview_image);
            linearLayout = (RelativeLayout) view.findViewById(R.id.linearLayout);
        }
    }
    @Override
    public int getItemCount() {
        return Imageid.length;
    }


}

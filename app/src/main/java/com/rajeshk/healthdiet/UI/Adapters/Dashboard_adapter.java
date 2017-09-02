package com.rajeshk.healthdiet.UI.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rajeshk.healthdiet.Presenters.Api_Client.Retrofit_Client;
import com.rajeshk.healthdiet.Presenters.Helper.Constants;
import com.rajeshk.healthdiet.R;

/**
 * Created by ChRajeshKumar on 15-Apr-17.
 */

public class Dashboard_adapter extends  RecyclerView.Adapter<Dashboard_adapter.MyViewHolder>  {
    private Context mContext;
    private final String[] string;
    private final Integer[] Imageid;
    View itemView;
    Integer[] images = Constants.getInstance().getDashBordImages();

    public Dashboard_adapter(Context c, String[] string, Integer[] Imageid) {
        mContext = c;
        this.Imageid = Imageid;
        this.string = string;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView gridview_text;
        ImageView gridview_image;
        RelativeLayout linearLayout;



        public MyViewHolder(View view) {
            super(view);
            gridview_text = (TextView) view.findViewById(R.id.gridview_text);
            gridview_image = (ImageView) view.findViewById(R.id.gridview_image);
            linearLayout = (RelativeLayout) view.findViewById(R.id.linearLayout);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.category_item, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.gridview_text.setText(string[position]);

        holder.gridview_image.setBackgroundResource(images[position]);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit_Client.getInstance().getResponse(mContext.getResources().getStringArray(R.array.videos_list)[position],50,mContext);
            }
        });
    }

    @Override
    public int getItemCount() {
        return string.length;
    }
}

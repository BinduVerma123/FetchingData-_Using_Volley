package com.example.volley.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.volley.activities.AnimeActivity;
import com.example.volley.model.Anime;
import com.example.volley.R;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    private Context mContext;
    private List<Anime> mData;
    RequestOptions option;

    public RecyclerViewAdapter(Context mContext, List<Anime> mData) {
        this.mContext = mContext;
        this.mData = mData;

        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view=inflater.inflate(R.layout.anime_row_item,parent,false);
        final MyViewHolder viewHolder = new MyViewHolder(view);
        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, AnimeActivity.class);
                i.putExtra("anime name",mData.get(viewHolder.getAdapterPosition()).getName());
                i.putExtra("anime description",mData.get(viewHolder.getAdapterPosition()).getDescription());
                i.putExtra("anime studio",mData.get(viewHolder.getAdapterPosition()).getStudio());
                i.putExtra("anime category ",mData.get(viewHolder.getAdapterPosition()).getCategorie());
                i.putExtra("anime nb_episode",mData.get(viewHolder.getAdapterPosition()).getNb_episode());
                i.putExtra("anime rating",mData.get(viewHolder.getAdapterPosition()).getRating());
                i.putExtra("anime img",mData.get(viewHolder.getAdapterPosition()).getImage_url());

            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.tv_name.setText(mData.get(i).getName());
        myViewHolder.tv_studio.setText(mData.get(i).getStudio());
        myViewHolder.tv_rating.setText(mData.get(i).getRating());
        myViewHolder.tv_category.setText(mData.get(i).getCategorie());

        Glide.with(mContext).load(mData.get(i).getImage_url()).apply(option).into(myViewHolder.img_thumbnail);

    }

    @Override
    public int getItemCount() {
       return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv_name;
        TextView tv_studio;
        TextView tv_category;
        TextView tv_rating;
        ImageView img_thumbnail;
        LinearLayout view_container;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            view_container=itemView.findViewById(R.id.container);
            tv_name=itemView.findViewById(R.id.anime_name);
            tv_category=itemView.findViewById(R.id.categories);
            tv_rating=itemView.findViewById(R.id.rating);
            tv_studio=itemView.findViewById(R.id.studio);
            img_thumbnail=itemView.findViewById(R.id.thumbnail);
        }
    }


}

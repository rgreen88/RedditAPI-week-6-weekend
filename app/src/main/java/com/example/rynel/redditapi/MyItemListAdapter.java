package com.example.rynel.redditapi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.rynel.redditapi.model.Child;

import java.util.ArrayList;
import java.util.List;

public class MyItemListAdapter extends RecyclerView.Adapter<MyItemListAdapter.ViewHolder> {

    private static final String TAG = "MyItemListAdapter";
    Context context;
    List<Child> items = new ArrayList<>();

    public MyItemListAdapter(List<Child> itemList) {
        this.items = itemList;
    }

    @Override
    public MyItemListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();

        View view = LayoutInflater
                .from( parent.getContext() )
                .inflate( R.layout.list_item, parent, false );

        return new ViewHolder( view );
    }

    @Override
    public void onBindViewHolder(MyItemListAdapter.ViewHolder holder, final int position) {
        Child c = items.get( position );

        holder.title.setText( c.getData().getTitle());
        holder.author.setText( c.getData().getAuthor());

        Glide.with( context )
                .load( c.getData().getThumbnail() )
                .into( holder.thumbnail );
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView thumbnail;
        TextView title, author;

        public ViewHolder(View itemView) {
            super(itemView);

            author = itemView.findViewById( R.id.tvAuthor );
            title = itemView.findViewById( R.id.tvTitle );
            thumbnail = itemView.findViewById( R.id.ivThumbnail );
        }
    }
}
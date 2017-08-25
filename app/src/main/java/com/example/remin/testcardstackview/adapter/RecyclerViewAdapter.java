package com.example.remin.testcardstackview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.remin.testcardstackview.Item;
import com.example.remin.testcardstackview.R;

import java.util.List;

/**
 * Created by remin.
 * Created Time 2017/8/24 ${Time}
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<Item> mItems;
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.item_image);
            this.textView = (TextView)itemView.findViewById(R.id.item_id);
        }
    }

    public RecyclerViewAdapter(List<Item> items) {
        this.mItems = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item item = mItems.get(position);
        holder.textView.setText(item.getItemName());
        holder.imageView.setImageResource(item.getItemId());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}

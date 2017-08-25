package com.example.remin.testcardstackview.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.remin.testcardstackview.Item;
import com.example.remin.testcardstackview.R;
import com.loopeer.cardstack.CardStackView;
import com.loopeer.cardstack.StackAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by remin.
 * Created Time 2017/8/21 ${Time}
 */

public class TestStackAdapter extends StackAdapter<Integer>{
//    private List<Item> items = new ArrayList<>();
    private Context mContext;
    public TestStackAdapter(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    protected CardStackView.ViewHolder onCreateView(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case R.layout.single_item:
                view = getLayoutInflater().inflate(R.layout.single_item,parent,false);

//                RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
//                LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
//                recyclerView.setLayoutManager(layoutManager);
//                RecyclerViewAdapter adapter = new RecyclerViewAdapter(items);
//                recyclerView.setAdapter(adapter);

                return new ColorItemLargeHeaderViewHolder(view,mContext);
        }
        return null;
    }

    @Override
    public void bindView(Integer data, int position, CardStackView.ViewHolder holder) {
        if(holder instanceof ColorItemLargeHeaderViewHolder){
            ColorItemLargeHeaderViewHolder h = (ColorItemLargeHeaderViewHolder)holder;
            h.onBind(data,position);
        }
    }


    @Override
    public int getItemViewType(int position) {
        if(position==6){
            return R.layout.single_item;
        }
        return  R.layout.single_item;
    }

    static class ColorItemViewHolder extends CardStackView.ViewHolder {
        View mLayout;
        View mContainerContent;
        TextView mTextTitle;

        public ColorItemViewHolder(View view) {
            super(view);
            mLayout = view.findViewById(R.id.frame_list_card_item);
            mContainerContent = view.findViewById(R.id.container_list_content);
            mTextTitle = (TextView) view.findViewById(R.id.text_list_card_title);

        }

        @Override

        public void onItemExpand(boolean b) {
            mContainerContent.setVisibility(b ? View.VISIBLE : View.GONE);
        }

        public void onBind(Integer data, int position) {
            mLayout.getBackground().setColorFilter(ContextCompat.getColor(getContext(), data), PorterDuff.Mode.SRC_IN);
            mTextTitle.setText(String.valueOf(position));
        }
    }

    static class ColorItemLargeHeaderViewHolder extends CardStackView.ViewHolder{
        View mLayout;
        View mContainerContent;
        TextView mTextTitle;
        private List<Item> items = new ArrayList<>();

        public ColorItemLargeHeaderViewHolder(View view,Context context) {
            super(view);
            this.mLayout = view.findViewById(R.id.frame_list_card_item);
            this.mContainerContent = view.findViewById(R.id.container_list_content);
            this.mTextTitle = (TextView) view.findViewById(R.id.text_list_card_title);

            initItems();
            RecyclerView recyclerView = (RecyclerView) mContainerContent.findViewById(R.id.recycler_view);
            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            recyclerView.setLayoutManager(layoutManager);
            RecyclerViewAdapter adapter = new RecyclerViewAdapter(items);
            recyclerView.setAdapter(adapter);

        }

        @Override
        public void onItemExpand(boolean b) {
            mContainerContent.setVisibility(b?View.VISIBLE:View.GONE);
        }

        @Override
        protected void onAnimationStateChange(int state, boolean willBeSelect) {
            super.onAnimationStateChange(state, willBeSelect);

            if(state==CardStackView.ANIMATION_STATE_START&&willBeSelect){
                onItemExpand(true);
            }
            if(state==CardStackView.ANIMATION_STATE_END&&willBeSelect){
                onItemExpand(false);
            }
        }
        public void onBind(Integer data,int position){
            mLayout.getBackground().setColorFilter(ContextCompat.getColor(getContext(),data),PorterDuff.Mode.SRC_IN);
            mTextTitle.setText(String.valueOf(position));
            itemView.findViewById(R.id.recycler_view).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((CardStackView)itemView.getParent()).performItemClick(ColorItemLargeHeaderViewHolder.this);
                }
            });
        }


    public void initItems(){
        for (int i=0;i<2;i++){
            Item item = new Item(R.drawable.eye1,"eye1");
            try{
                items.add(item);
            }catch (Exception e){
                e.printStackTrace();
            }


            Item item1 = new Item(R.drawable.eye2,"eye2");
            items.add(item);
        }
    }
    }
}

package com.example.remin.testcardstackview;

import android.os.Handler;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.example.remin.testcardstackview.adapter.RecyclerViewAdapter;
import com.example.remin.testcardstackview.adapter.TestStackAdapter;
import com.loopeer.cardstack.AllMoveDownAnimatorAdapter;
import com.loopeer.cardstack.CardStackView;
import com.loopeer.cardstack.UpDownAnimatorAdapter;
import com.loopeer.cardstack.UpDownStackAnimatorAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.support.v7.appcompat.R.styleable.View;
import static android.support.v7.appcompat.R.styleable.View_android_focusable;

public class MainActivity extends AppCompatActivity implements CardStackView.ItemExpendListener {

    private List<Item> items = new ArrayList<>();
    public static Integer[] TEST_DATAS = new Integer[]{
            R.color.color_1,
            R.color.color_2,
            R.color.color_3,
            R.color.color_4,
            R.color.color_5,
            R.color.color_6,
            R.color.color_7,
            R.color.color_8,
            R.color.color_9,
            R.color.color_10,
            R.color.color_11,
            R.color.color_12
    };

    private CardStackView mCardStackView;
    private LinearLayout mActionButtonContainer;

    private TestStackAdapter mTestStackAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mCardStackView = (CardStackView)findViewById(R.id.stackview_main);
        mActionButtonContainer = (LinearLayout)findViewById(R.id.button_container);
        mCardStackView.setItemExpendListener(this);
        mTestStackAdapter = new TestStackAdapter(this);
        mCardStackView.setAdapter(mTestStackAdapter);

//        initItems();
//
//        View view = LayoutInflater.from(this).inflate(R.layout.single_item,mCardStackView,false);
//
//        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//        RecyclerViewAdapter adapter = new RecyclerViewAdapter(items);
//        recyclerView.setAdapter(adapter);

        new Handler().postDelayed(
                new Runnable() {
            @Override
            public void run() {
                mTestStackAdapter.updateData(Arrays.asList(TEST_DATAS));
            }
        }
        ,200);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actions,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_all_down:
                mCardStackView.setAnimatorAdapter(new AllMoveDownAnimatorAdapter(mCardStackView));
                break;
            case R.id.menu_up_down:
                mCardStackView.setAnimatorAdapter(new UpDownAnimatorAdapter(mCardStackView));
                break;
            case R.id.menu_up_down_stack:
                mCardStackView.setAnimatorAdapter(new UpDownStackAnimatorAdapter(mCardStackView));
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    public void onPreClick(View view){
        mCardStackView.pre();
    }

    public void onNextClick(View view){
        mCardStackView.next();
    }

    @Override
    public void onItemExpend(boolean expend) {
        mActionButtonContainer.setVisibility(expend? android.view.View.VISIBLE: android.view.View.GONE);
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

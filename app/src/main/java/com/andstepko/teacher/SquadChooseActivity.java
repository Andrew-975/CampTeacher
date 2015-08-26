package com.andstepko.teacher;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.andstepko.teacher.Logic.Child;
import com.andstepko.teacher.Logic.ChildItem;
import com.andstepko.teacher.Logic.MyData;

import java.util.ArrayList;

public class SquadChooseActivity extends Activity {

    private static final int CHECKED_COLOR = Color.YELLOW;
    private static final int SECOND_GROUP_COLOR = Color.WHITE;
    //private static final int SECOND_GROUP_COLOR = R.color.my_comparator_button_unchecked;
    private static final int FIRST_GROUP_COLOR = Color.TRANSPARENT;

    public static AchievementsTabActivity calledActivity;

    private ArrayList<Child> list;

    protected void beforeClosing(){
        calledActivity.captureTempRecords();
    }

    protected void refreshListItemColors(ChildItemAdapter childItemAdapter){
        ListView listView = (ListView)findViewById(R.id.listViewSquadChoose);
        int offset = listView.getFirstVisiblePosition();
        int listSize = listView.getLastVisiblePosition() - listView.getFirstVisiblePosition() + 1;
        int count = 0;

        for(int i = 0; i < listSize; i++){
            ChildItem childItem = childItemAdapter.getChildItem(offset + i);
            View listItem = listView.getChildAt(i);

            if(childItem.getFlag()){
                // Chose ChildItem.
                listItem.setBackgroundColor(CHECKED_COLOR);
                count++;
                continue;
            }
            else{
                if(childItem.getChild().getGroup()){
                    // Second group.
                    listItem.setBackgroundColor(SECOND_GROUP_COLOR);
                    continue;
                }
                else{
                    // First group.
                    listItem.setBackgroundColor(FIRST_GROUP_COLOR);
                }
            }
        }

        setQuantityText(count);
    }

    protected void setQuantityText(int count){
        TextView quantityTextView = (TextView)findViewById(R.id.squadChooseQuantityTextView);
        quantityTextView.setText(String.valueOf(count));
    }

    protected void itemClick(ListView listView, ChildItemAdapter childItemAdapter, int i){
        ChildItem childItem = childItemAdapter.getChildItem(i);
        childItem.revertFlag();
        if (childItem.getFlag()) {
            // Paint in yellow.
            MyData.tempChildren.add(childItem.getChild());
            //int offset = listView.getFirstVisiblePosition();
            //listView.getChildAt(i - offset).setBackgroundColor(Color.YELLOW);
        } else {
            MyData.tempChildren.remove(childItem.getChild());
            //int offset = listView.getFirstVisiblePosition();
            //listView.getChildAt(i - offset).setBackgroundColor(Color.TRANSPARENT);
        }
        refreshListItemColors(childItemAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_squad_choose);

        list = MyData.Data.squad.getChildren();
        final ChildItemAdapter childItemAdapter = new ChildItemAdapter(list, MyData.Data.squad.getChildSortType(), this);

        final ListView listView = (ListView)findViewById(R.id.listViewSquadChoose);
        listView.setAdapter(childItemAdapter);

        refreshListItemColors(childItemAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                itemClick(listView, childItemAdapter, i);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                itemClick(listView, childItemAdapter, i);
                finish();
                return true;
            }
        });

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
                switch (scrollState) {
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                        // when list scrolling stops
                        refreshListItemColors(childItemAdapter);
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_FLING:
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) { }
        });
    }

    @Override
    protected void onDestroy() {
        beforeClosing();
        super.onDestroy();
    }
}

package com.andstepko.teacher;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Environment;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.andstepko.teacher.Logic.Child;
import com.andstepko.teacher.Logic.ChildItem;
import com.andstepko.teacher.Logic.ChildSortType;
import com.andstepko.teacher.Logic.MyData;
import com.andstepko.teacher.Logic.Squad;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class SquadEditActivity extends Activity {

    private static final String SQUAD_FILE_NAME = "squad.txt";
    private static final int SECOND_GROUP_COLOR = Color.WHITE;
    private static final int FIRST_GROUP_COLOR = Color.TRANSPARENT;

    private static Child removalApplicant;

    protected Squad getSquad(){
        return MyData.Data.squad;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_squad_edit);

        ListView listView = (ListView)findViewById(R.id.listViewSquadEdit);

//        ChildItemAdapter childItemAdapter = new ChildItemAdapter(MyData.Data.squad.getChildren(),
//                MyData.Data.squad.getChildSortType(), this);
        //listView.setAdapter(childItemAdapter);
        //refreshListItemColors(childItemAdapter);
        refreshListView();

        Button importButton = (Button)findViewById(R.id.importButton);
        Button addButton = (Button)findViewById(R.id.add_button_squad_edit);

        Button nameSortButton = (Button)findViewById(R.id.nameSortButton);
        Button surnameSortButton = (Button)findViewById(R.id.surnameSortButton);
        Button roomSortButton = (Button)findViewById(R.id.roomSortButton);
        Button ageSortButton = (Button)findViewById(R.id.ageSortButton);
        Button groupSortButton = (Button)findViewById(R.id.groupSortButton);

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
                switch (scrollState) {
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                        // when list scrolling stops
                        refreshListItemColors((ChildItemAdapter)absListView.getAdapter());
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_FLING:
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {

            }
        });

        importButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                // Import from file.
                try {
                    importSquadFromFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                refreshListView();
                return false;
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNewChildDialog(view.getContext());
            }
        });

        nameSortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSquad().sortByName();
                refreshSortButtonColors();
                refreshListView();
            }
        });

        surnameSortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSquad().sortBySurname();
                refreshSortButtonColors();
                refreshListView();
            }
        });

        roomSortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSquad().sortByRoom();
                refreshSortButtonColors();
                refreshListView();
            }
        });

        ageSortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSquad().sortByAge();
                refreshSortButtonColors();
                refreshListView();
            }
        });

        groupSortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSquad().sortByGroup();
                refreshSortButtonColors();
                refreshListView();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showChildEditDialog(((ChildItem) adapterView.getAdapter().getItem(i)).getChild(), view.getContext());
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                showChildRemoveDialog(((ChildItem) adapterView.getAdapter().getItem(i)).getChild(), view.getContext());

                return true;
            }
        });
    }

    // region SortButtons
    protected void refreshSortButtonColors(){
        unpaintButtons();
        paintCheckedSortButton();
    }

    protected void paintCheckedSortButton(){
        Button button = null;
        ChildSortType childSortType = getSquad().getChildSortType();

        switch (childSortType){
            case NAME: button = (Button)findViewById(R.id.nameSortButton);
                break;
            case SURNAME: button = (Button)findViewById(R.id.surnameSortButton);
                break;
            case ROOM: button = (Button)findViewById(R.id.roomSortButton);
                break;
            case AGE: button = (Button)findViewById(R.id.ageSortButton);
                break;
            case GROUP: button = (Button)findViewById(R.id.groupSortButton);
                break;
        }

        if(button != null){
            paintCheckedSortButton(button);
        }
    }

    protected void paintCheckedSortButton(Button button){
        button.setBackgroundColor(getResources().getColor(R.color.my_comparator_button_checked));
    }

    protected void unpaintButtons(){
        paintSortButtons(Color.TRANSPARENT);
    }

    protected void paintSortButtons(int color){
        Button nameSortButton = (Button)findViewById(R.id.nameSortButton);
        Button surnameSortButton = (Button)findViewById(R.id.surnameSortButton);
        Button roomSortButton = (Button)findViewById(R.id.roomSortButton);
        Button ageSortButton = (Button)findViewById(R.id.ageSortButton);
        Button groupSortButton = (Button)findViewById(R.id.groupSortButton);

        nameSortButton.setBackgroundColor(color);
        surnameSortButton.setBackgroundColor(color);
        roomSortButton.setBackgroundColor(color);
        ageSortButton.setBackgroundColor(color);
        groupSortButton.setBackgroundColor(color);
    }
    // endregion SortButtons

    public void refreshListView(){
        getSquad().sortByCurrentComparator();
        ArrayList<Child> childrenList = getSquad().getChildren();
        ChildItemAdapter childItemAdapter = new ChildItemAdapter(childrenList,
                     MyData.Data.squad.getChildSortType(), this);

        ListView listView = (ListView)findViewById(R.id.listViewSquadEdit);

        listView.setAdapter(childItemAdapter);
        refreshListItemColors(childItemAdapter);

        int count = childItemAdapter.getCount();
        refreshQuantityTextView(count);
    }

    protected void refreshListItemColors(ChildItemAdapter childItemAdapter){
        ListView listView = (ListView)findViewById(R.id.listViewSquadEdit);
        int offset = listView.getFirstVisiblePosition();
        int listSize = listView.getLastVisiblePosition() - listView.getFirstVisiblePosition() + 1;

        for(int i = 0; i < listSize; i++){
            Child child = childItemAdapter.getChildItem(offset + i).getChild();
            View listItem = listView.getChildAt(i);

            if(child.getGroup()){
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

    protected void refreshQuantityTextView(int count){
        TextView quantityTextView = (TextView)findViewById(R.id.squadEditQuantityTextView);
        quantityTextView.setText(String.valueOf(count));
    }

    protected void showChildEditDialog(Child child, Context context){
        ChildEditDialogActivity.child = child;
        ChildEditDialogActivity.calledActivity = this;
        Intent intent=new Intent(getApplicationContext(), ChildEditDialogActivity.class);
        startActivity(intent);
    }

    protected void  showNewChildDialog(Context context){
        NewChildDialogActivity.calledActivity = this;
        Intent intent=new Intent(getApplicationContext(), NewChildDialogActivity.class);
        startActivity(intent);
    }

    protected void showChildRemoveDialog(Child child, Context context){
        removalApplicant = child;
        ChildRemoveDialogActivity.child = child;
        ChildRemoveDialogActivity.calledActivity = this;
        Intent intent=new Intent(getApplicationContext(), ChildRemoveDialogActivity.class);
        startActivity(intent);
    }

    public void removeApplicant(){
        getSquad().removeChild(removalApplicant);
        refreshListView();
    }

    public void addChild(Child child){
        getSquad().addChild(child);
        refreshListView();
    }

    public void cancelRemovalApplicant(){
        removalApplicant = null;
    }

    protected void importSquadFromFile() throws IOException {
        importSquadFromFile(SQUAD_FILE_NAME);
    }
    protected void importSquadFromFile(String fileName) throws IOException {
        File sdcard = Environment.getExternalStorageDirectory();
        File squadFile = new File(sdcard, fileName);
        Scanner scanner = new Scanner(squadFile);
        FileReader fileReader = new FileReader(squadFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String childString;

        MyData.Data.squad = new Squad();
        while(((childString = bufferedReader.readLine()) != null) &&
                (Child.canBeChildString(childString))){
            MyData.Data.squad.addChild(new Child(childString));
        }
    }
}
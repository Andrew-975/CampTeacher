package com.andstepko.teacher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.andstepko.teacher.Logic.Child;
import com.andstepko.teacher.Logic.ChildRecord;
import com.andstepko.teacher.Logic.MyData;

import java.util.ArrayList;


public class StatisticsActivity extends Activity {

    private static final int CHAPTERS = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        ListView listView = (ListView)findViewById(R.id.statisticsListView);

        refreshListView();
        showNotesDialog();

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                removeChildRecord(i);
                refreshListView();
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshListView();
    }

    protected void refreshListView(){
        final ChildRecordAdapter childRecordAdapter = new ChildRecordAdapter(getData(), this);
        ListView listView = (ListView)findViewById(R.id.statisticsListView);
        listView.setAdapter(childRecordAdapter);
    }

    protected ArrayList<ChildRecord> getData(){
        ArrayList<ChildRecord> result = new ArrayList<ChildRecord>();

        result.add(new ChildRecord(new Child("_______________________________Plates")));
        result.addAll(MyData.Data.platesAchievements);
        result.add(new ChildRecord(new Child("_______________________________Rooms")));
        result.addAll(MyData.Data.roomsAchievements);
        result.add(new ChildRecord(new Child("_______________________________Sleep")));
        result.addAll(MyData.Data.sleepAchievements);
        result.add(new ChildRecord(new Child("_______________________________Other")));
        result.addAll(MyData.Data.otherAchievements);
        result.add(new ChildRecord(new Child("_______________________________Lessons")));
        result.addAll(MyData.Data.lessonsAchievements);
        result.add(new ChildRecord(new Child("_______________________________Warnings!")));
        result.addAll(MyData.Data.warningsAchievements);

        return  result;
    }

    protected void showNotesDialog(){
        Intent intent=new Intent(getApplicationContext(), NotesDialogActivity.class);
        startActivity(intent);
    }

    protected ArrayList<ChildRecord> getAchievementList(int listIndex){
        switch (listIndex){
            case 0: return MyData.Data.platesAchievements;
            case 1: return MyData.Data.roomsAchievements;
            case 2: return MyData.Data.sleepAchievements;
            case 3: return MyData.Data.otherAchievements;
            case 4: return MyData.Data.lessonsAchievements;
            case 5: return MyData.Data.warningsAchievements;
        }
        return new ArrayList<ChildRecord>();
    }

    protected int[] getStartingPositionsOfLists(){
        int result[] = new int[CHAPTERS];

        result[0] = 0;
        for(int i = 1; i < result.length; i++){
            result[i] = getAchievementList(i-1).size() + 1 + result[i - 1];
        }

        return result;
    }

    protected void removeChildRecord(ArrayList<ChildRecord> arrayList, int position){
        if(arrayList.size() > position){
            arrayList.remove(position);
        }
    }

    protected void removeChildRecord(int listIndex, int localPosition){
        removeChildRecord(getAchievementList(listIndex), localPosition);
    }

    protected void removeChildRecord(int globalPosition){
        if(globalPosition < 0){
            return;
        }

        int listSizes[] = getStartingPositionsOfLists();
        int i = CHAPTERS - 1;

        while(globalPosition < listSizes[i]){
            i--;
        }

        if(globalPosition == listSizes[i]){
            return;
        }
        // Click not on caption item.
        removeChildRecord(i, globalPosition - listSizes[i] - 1);
    }
}

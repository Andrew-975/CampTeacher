package com.andstepko.teacher;


import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.andstepko.teacher.Logic.ChildRecord;
import com.andstepko.teacher.Logic.MyData;

import java.util.ArrayList;

public class PlatesTabActivity extends AchievementsTabActivity {


    @Override
    protected ArrayList<ChildRecord> getData() {
        return MyData.Data.platesAchievements;
    }

    @Override
    protected ListView getParticularListView() {
        View view = findViewById(R.id.listViewPlates);
        return (ListView)view;
    }

    @Override
    protected Button getParticularAddButton() {
        return (Button)findViewById(R.id.add_button_plates);
    }

    @Override
    protected void mySetContentView() {
        setContentView(R.layout.activity_plates_tab);
    }
}

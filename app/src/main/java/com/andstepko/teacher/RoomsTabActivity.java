package com.andstepko.teacher;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.andstepko.teacher.Logic.ChildRecord;
import com.andstepko.teacher.Logic.MyData;

import java.util.ArrayList;


public class RoomsTabActivity extends AchievementsTabActivity {

    @Override
    protected void mySetContentView() {
        setContentView(R.layout.activity_rooms_tab);
    }

    @Override
    protected ArrayList<ChildRecord> getData() {
        return MyData.Data.roomsAchievements;
    }

    @Override
    protected ListView getParticularListView() {
        View view = findViewById(R.id.listViewRooms);
        return (ListView)view;
    }

    @Override
    protected Button getParticularAddButton() {
        return (Button)findViewById(R.id.add_button_rooms);
    }
}

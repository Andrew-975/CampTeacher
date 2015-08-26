package com.andstepko.teacher;

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


public class WarningsTabActivity extends AchievementsTabActivity{
    @Override
    protected void mySetContentView() {
        setContentView(R.layout.activity_warnings_tab);
    }

    @Override
    protected ArrayList<ChildRecord> getData() {
        return MyData.Data.warningsAchievements;
    }

    @Override
    protected ListView getParticularListView() {
        View view = findViewById(R.id.listViewWarnings);
        return (ListView)view;
    }

    @Override
    protected Button getParticularAddButton() {
        return (Button)findViewById(R.id.add_button_warnings);
    }
}

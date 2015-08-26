package com.andstepko.teacher;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.andstepko.teacher.Logic.Child;
import com.andstepko.teacher.Logic.ChildRecord;
import com.andstepko.teacher.Logic.MyData;

import java.util.ArrayList;


public abstract class AchievementsTabActivity extends Activity {

    private Button addButton;

    public void captureTempRecords(){
        getData().addAll(MyData.getChildRecordsFromTempChildren());
        MyData.tempChildren = new ArrayList<Child>();
        resetListView();
    }

    public void resetListView(){
        ChildRecordAdapter childRecordAdapter = new ChildRecordAdapter(getData(), this);

        ListView listView = getParticularListView();
        listView.setAdapter(childRecordAdapter);
    }

    protected abstract ListView getParticularListView(); //(ListView)findViewById(R.id.listView)

    protected abstract Button getParticularAddButton();

    protected abstract void mySetContentView();

    protected abstract ArrayList<ChildRecord> getData();

    protected void showRemarksDialog(ChildRecord childRecord){
        Intent intent = new Intent(getApplicationContext(), ChildRecordRemarksDialogActivity.class);
        MyData.tempChildRecord = childRecord;
        ChildRecordRemarksDialogActivity.calledActivity = this;

        startActivity(intent);
    }

    protected void showForeignChildNameDialog(){
        ChildRecord childRecord = new ChildRecord();
        getData().add(childRecord);
        MyData.tempChildRecord = childRecord;
        ForeignChildNameDialogActivity.calledActivity = this;

        Intent intent = new Intent(getApplicationContext(), ForeignChildNameDialogActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mySetContentView();

        final ChildRecordAdapter childRecordAdapter = new ChildRecordAdapter(getData(), this);
        ListView listView = getParticularListView();
        addButton = getParticularAddButton();

        listView.setAdapter(childRecordAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ChildRecord childRecord = childRecordAdapter.getChildRecord(i);
                showRemarksDialog(childRecord);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AchievementsTabActivity calledActivity = (AchievementsTabActivity) view.getContext();

                calledActivity.getData().remove(i);
                resetListView();

                return true;
            }
        });

        // AddButton
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // SquadChoose
                Context context = view.getContext();
                SquadChooseActivity.calledActivity = (AchievementsTabActivity) context;

                Intent intent=new Intent(getApplicationContext(), SquadChooseActivity.class);
                startActivity(intent);
            }
        });

        addButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                // Add foreign child.
                showForeignChildNameDialog();

//                getData().add(new ChildRecord(new Child()));
//                resetListView();

                return true;
            }
        });
    }


}

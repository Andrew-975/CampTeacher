package com.andstepko.teacher;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.andstepko.teacher.Logic.Child;
import com.andstepko.teacher.Logic.ChildRecord;
import com.andstepko.teacher.Logic.MyData;


public class ForeignChildNameDialogActivity extends Activity {

    private static final String FIVE_UNDERLINES = "_____";

    public static AchievementsTabActivity calledActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_of_foreign_child_dialog);

        Button okButton = (Button)findViewById(R.id.nameOfForeignChildOkButton);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText nameEditText = (EditText) findViewById(R.id.nameOfForeignChildEditText);
                String newName = FIVE_UNDERLINES + nameEditText.getText().toString();
                // Change remarks
                MyData.tempChildRecord.setChildDangerous(new Child(newName));
                MyData.tempChildRecord = new ChildRecord();
                // Refresh the listView
                calledActivity.resetListView();
                finish();
            }
        });
    }
}

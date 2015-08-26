package com.andstepko.teacher;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.andstepko.teacher.Logic.ChildRecord;
import com.andstepko.teacher.Logic.MyData;


public class ChildRecordRemarksDialogActivity extends Activity {

    public static AchievementsTabActivity calledActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_record_remarks_dialog);

        EditText editText = (EditText)findViewById(R.id.childRecordRemarksEditText);
        editText.setText(MyData.tempChildRecord.getRecord());

        Button okButton = (Button)findViewById(R.id.childRecordRemarksOkButton);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get new remarks string
                EditText remarksEditText = (EditText)findViewById(R.id.childRecordRemarksEditText);
                String newRemark = remarksEditText.getText().toString();
                // Change remarks
                MyData.tempChildRecord.setRecord(newRemark);
                MyData.tempChildRecord = new ChildRecord();
                // Refresh the listView
                calledActivity.resetListView();
                finish();
            }
        });
    }
}

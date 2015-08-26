package com.andstepko.teacher;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.andstepko.teacher.Logic.Child;


public class ChildRemoveDialogActivity extends Activity {

    public static SquadEditActivity calledActivity;
    public static Child child;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_remove_dialog);

        TextView textView = (TextView)findViewById(R.id.childRemoveTextView);
        textView.setText("Are sure you want to remove\n" + child.toString());

        Button okButton = (Button)findViewById(R.id.childRemoveOkButton);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calledActivity.removeApplicant();
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        calledActivity.cancelRemovalApplicant();
        super.onDestroy();
    }
}

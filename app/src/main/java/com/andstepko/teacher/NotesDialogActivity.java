package com.andstepko.teacher;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.andstepko.teacher.Logic.MyData;


public class NotesDialogActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_dialog);

        EditText editText = (EditText)findViewById(R.id.notesDialogEditText);
        Button okButton = (Button)findViewById(R.id.notesDialogOkButton);

        editText.setText(MyData.Data.notepad);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = (EditText)findViewById(R.id.notesDialogEditText);
                MyData.Data.notepad = editText.getText().toString();
                finish();
            }
        });
    }
}

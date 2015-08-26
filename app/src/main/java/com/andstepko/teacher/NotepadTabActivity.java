package com.andstepko.teacher;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.andstepko.teacher.Logic.MyData;


public class NotepadTabActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad_tab);

        loadNotepad();

        EditText editText = (EditText)findViewById(R.id.notepadEditText);

        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                saveNotepad();
            }
        });
    }

    private void saveNotepad(){
        EditText editText = (EditText)findViewById(R.id.notepadEditText);
        MyData.Data.notepad = editText.getText().toString();
    }

    private void loadNotepad() {
        EditText editText = (EditText) findViewById(R.id.notepadEditText);
        editText.setText(MyData.Data.notepad);
    }
}

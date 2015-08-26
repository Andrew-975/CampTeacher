package com.andstepko.teacher;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.andstepko.teacher.Logic.Child;


public class NewChildDialogActivity extends Activity {

    private static final String EMPTY_STRING = "";

    public static SquadEditActivity calledActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_child_dialog);

        fillEditTextHints();

        Button okButton = (Button)findViewById(R.id.newChildOkButton);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addChild();
                finish();
            }
        });
    }

    protected void fillEditTextHints(){
        EditText nameEditText = (EditText)findViewById(R.id.newChildNameEditText);
        EditText surnameEditText = (EditText)findViewById(R.id.newChildSurnameEditText);
        EditText ageEditText = (EditText)findViewById(R.id.newChildAgeEditText);
        EditText roomEditText = (EditText)findViewById(R.id.newChildRoomEditText);
        EditText groupEditText = (EditText)findViewById(R.id.newChildGroupEditText);
        EditText remarksEditText = (EditText)findViewById(R.id.newChildRemarksEditText);

        nameEditText.setHint("name");
        surnameEditText.setHint("surname");
        ageEditText.setHint("age");
        roomEditText.setHint("room");
        groupEditText.setHint("group");
        remarksEditText.setHint("remarks");
    }

    protected void addChild(){
        EditText nameEditText = (EditText)findViewById(R.id.newChildNameEditText);
        EditText surnameEditText = (EditText)findViewById(R.id.newChildSurnameEditText);
        EditText ageEditText = (EditText)findViewById(R.id.newChildAgeEditText);
        EditText roomEditText = (EditText)findViewById(R.id.newChildRoomEditText);
        EditText groupEditText = (EditText)findViewById(R.id.newChildGroupEditText);
        EditText remarksEditText = (EditText)findViewById(R.id.newChildRemarksEditText);

        String name = nameEditText.getText().toString();
        String surname = surnameEditText.getText().toString();
        String ageString = ageEditText.getText().toString();
        int age = Child.NO_AGE;
        if(!ageString.equals(EMPTY_STRING)){
            age = Integer.valueOf(ageString);
        }
        String roomString = roomEditText.getText().toString();
        double room = Child.NO_ROOM;
        if(!roomString.equals(EMPTY_STRING)){
            room = Double.valueOf(roomString);
        }
        String groupString = groupEditText.getText().toString();
        int groupInt = 0;
        if(!groupString.equals(EMPTY_STRING)){
            groupInt = Integer.valueOf(groupString);
        }
        boolean group = !((groupInt == 0) || (groupInt == 1));
        String remarks = remarksEditText.getText().toString();

        Child child = new Child();

        child.setName(name);
        child.setSurname(surname);
        child.setAge(age);
        child.setRoom(room);
        child.setGroup(group);
        child.setRemarks(remarks);

        calledActivity.addChild(child);
    }
}

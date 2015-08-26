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


public class ChildEditDialogActivity extends Activity {

    private static final String EMPTY_STRING = "";

    public static Child child;
    public static SquadEditActivity calledActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_edit_dialog);

        final EditText nameEditText = (EditText)findViewById(R.id.childEditNameEditText);
        final EditText surnameEditText = (EditText)findViewById(R.id.childEditSurnameEditText);
        final EditText ageEditText = (EditText)findViewById(R.id.childEditAgeEditText);
        final EditText roomEditText = (EditText)findViewById(R.id.childEditRoomEditText);
        final EditText groupEditText = (EditText)findViewById(R.id.childEditGroupEditText);
        final EditText remarksEditText = (EditText)findViewById(R.id.childEditRemarksEditText);

        fillEditTexts();

        Button okButton = (Button)findViewById(R.id.childEditOkButton);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveChild();
                finish();
            }
        });
    }

    protected void fillEditTexts(){
        EditText nameEditText = (EditText)findViewById(R.id.childEditNameEditText);
        EditText surnameEditText = (EditText)findViewById(R.id.childEditSurnameEditText);
        EditText ageEditText = (EditText)findViewById(R.id.childEditAgeEditText);
        EditText roomEditText = (EditText)findViewById(R.id.childEditRoomEditText);
        EditText groupEditText = (EditText)findViewById(R.id.childEditGroupEditText);
        EditText remarksEditText = (EditText)findViewById(R.id.childEditRemarksEditText);

        String name = child.getName();
        String surname = child.getSurname();
        int age = child.getAge();
        double room = child.getRoom();
        boolean group = child.getGroup();
        String remarks = child.getRemarks();

        if(name == EMPTY_STRING){
            nameEditText.setHint("name");
        }
        else{
            nameEditText.setText(name);
        }

        if(surname == EMPTY_STRING){
            surnameEditText.setHint("surname");
        }
        else{
            surnameEditText.setText(surname);
        }

        if(age == Child.NO_AGE){
            ageEditText.setHint("age");
        }
        else{
            ageEditText.setText(String.valueOf(age));
        }

        if(room == Child.NO_ROOM){
            roomEditText.setHint("room");
        }
        else{
            roomEditText.setText(String.valueOf(room));
        }

        if(group){
            groupEditText.setText("2");
        }
        else{
            groupEditText.setText("1");
        }

        if(remarks == EMPTY_STRING){
            remarksEditText.setHint("remarks");
        }
        else{
            remarksEditText.setText(remarks);
        }
    }

    protected void saveChild(){
        EditText nameEditText = (EditText)findViewById(R.id.childEditNameEditText);
        EditText surnameEditText = (EditText)findViewById(R.id.childEditSurnameEditText);
        EditText ageEditText = (EditText)findViewById(R.id.childEditAgeEditText);
        EditText roomEditText = (EditText)findViewById(R.id.childEditRoomEditText);
        EditText groupEditText = (EditText)findViewById(R.id.childEditGroupEditText);
        EditText remarksEditText = (EditText)findViewById(R.id.childEditRemarksEditText);

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

        child.setName(name);
        child.setSurname(surname);
        child.setAge(age);
        child.setRoom(room);
        child.setGroup(group);
        child.setRemarks(remarks);

        calledActivity.refreshListView();
    }
}

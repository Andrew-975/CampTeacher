package com.andstepko.teacher;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

import com.andstepko.teacher.Logic.MyData;
import com.andstepko.teacher.Logic.MyDataInstance;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class MainActivity extends TabActivity {

    private static final String DATA_FILE_NAME = "dataFile";

    private String getDataFileFullPath(){
        return getApplicationContext().getFilesDir().getPath().toString() + "/" + DATA_FILE_NAME;
    }

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            loadData();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // create the TabHost that will contain the Tabs
        TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);

        TabHost.TabSpec tab1 = tabHost.newTabSpec("First Tab");
        TabHost.TabSpec tab2 = tabHost.newTabSpec("Second Tab");
        TabHost.TabSpec tab3 = tabHost.newTabSpec("Third tab");
        TabHost.TabSpec tab4 = tabHost.newTabSpec("Fourth tab");
        TabHost.TabSpec tab5 = tabHost.newTabSpec("Fifth tab");
        TabHost.TabSpec tab6 = tabHost.newTabSpec("Sixth tab");
        TabHost.TabSpec tab7 = tabHost.newTabSpec("Seventh tab");
        TabHost.TabSpec tab8 = tabHost.newTabSpec("Eight tab");
        TabHost.TabSpec tab9 = tabHost.newTabSpec("Ninth tab");

        // Set the Tab name and Activity
        // that will be opened when particular Tab will be selected

        tab1.setIndicator("Plate");
        tab1.setContent(new Intent(this,PlatesTabActivity.class));

        tab2.setIndicator("Room");
        tab2.setContent(new Intent(this,RoomsTabActivity.class));

        tab3.setIndicator("Sleep");
        tab3.setContent(new Intent(this,SleepTabActivity.class));

        tab4.setIndicator("Other");
        tab4.setContent(new Intent(this,OtherTabActivity.class));

        tab5.setIndicator("Lesson");
        tab5.setContent(new Intent(this,LessonsTabActivity.class));

        tab6.setIndicator("Warn!");
        tab6.setContent(new Intent(this,WarningsTabActivity.class));

        tab7.setIndicator("Stat");
        tab7.setContent(new Intent(this,StatisticsActivity.class));

        tab8.setIndicator("Squad");
        tab8.setContent(new Intent(this,SquadEditActivity.class));

        tab9.setIndicator("Note");
        tab9.setContent(new Intent(this,NotepadTabActivity.class));

        /** Add the tabs  to the TabHost to display. */
        tabHost.addTab(tab1);
        tabHost.addTab(tab2);
        tabHost.addTab(tab3);
        tabHost.addTab(tab4);
        tabHost.addTab(tab5);
        tabHost.addTab(tab6);
        tabHost.addTab(tab7);
        tabHost.addTab(tab8);
        tabHost.addTab(tab9);
    }

    @Override
    protected void onDestroy() {
        try {
            saveData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        try {
            saveData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.onPause();
    }

    protected void saveData() throws IOException {
        File dataFile = new File(getDataFileFullPath());
        boolean kcuf = dataFile.createNewFile();
        boolean exists = dataFile.exists();

        FileOutputStream fileOutputStream = new FileOutputStream(dataFile);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(MyData.Data);
        objectOutputStream.close();
        fileOutputStream.close();
    }

    protected void loadData() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(new File(getDataFileFullPath()));
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        MyData.Data = (MyDataInstance) objectInputStream.readObject();
        objectInputStream.close();
        fileInputStream.close();
    }

    protected void showNotesDialog(){
        Intent intent=new Intent(getApplicationContext(), NotesDialogActivity.class);
        startActivity(intent);
    }
}

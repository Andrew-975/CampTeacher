package com.andstepko.teacher.Logic;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by andrew on 26.07.15.
 */
public class MyDataInstance implements Serializable{

    public Squad squad = new Squad();

    public ArrayList<ChildRecord> platesAchievements = new ArrayList<ChildRecord>();
    public ArrayList<ChildRecord> roomsAchievements = new ArrayList<ChildRecord>();
    public ArrayList<ChildRecord> sleepAchievements = new ArrayList<ChildRecord>();
    public ArrayList<ChildRecord> warningsAchievements = new ArrayList<ChildRecord>();
    public ArrayList<ChildRecord> otherAchievements = new ArrayList<ChildRecord>();
    public ArrayList<ChildRecord> lessonsAchievements = new ArrayList<ChildRecord>();

    public String notepad = new String();
}

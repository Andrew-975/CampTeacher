package com.andstepko.teacher.Logic;

import java.util.ArrayList;

/**
 * Created by andrew on 26.07.15.
 */
public class MyData {

    public static MyDataInstance Data = new MyDataInstance();

    public static ChildRecord tempChildRecord = new ChildRecord();
    public static ArrayList<Child> tempChildren = new ArrayList<Child>();

    public static ArrayList<ChildRecord> getChildRecordsFromTempChildren(){
        return childRecordsFromChildren(tempChildren);
    }

    private static ArrayList<ChildRecord> childRecordsFromChildren(ArrayList<Child> children){
        ArrayList<ChildRecord> result = new ArrayList<ChildRecord>();

        if(children != null) {
            for (Child child : children) {
                result.add(new ChildRecord(child));
            }
        }
        return result;
    }
}

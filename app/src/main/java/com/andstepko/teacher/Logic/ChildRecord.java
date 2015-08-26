package com.andstepko.teacher.Logic;

import java.io.Serializable;

/**
 * Created by andrew on 21.07.15.
 */
public class ChildRecord implements Serializable{

    private static final String SEPARATOR = " ";
    private static final String NO_RECORD = "";

    private Child child;
    private String record;

    public ChildRecord(Child child, String record){
        this.child = child;
        this.record = record;
    }

    public ChildRecord(Child child){
        this(child, NO_RECORD);
    }

    public ChildRecord(){
        this(new Child());
    }

    //region GetSet
    public Child getChild() {
        return child;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public void setChildDangerous(Child child){
        this.child = child;
    }
    //endregion GetSet


    @Override
    public String toString() {
        return child.getName() + SEPARATOR + child.getShortSurname() + getRecord();
    }
}

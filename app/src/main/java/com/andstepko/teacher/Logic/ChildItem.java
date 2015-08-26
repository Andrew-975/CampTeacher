package com.andstepko.teacher.Logic;

import java.io.Serializable;

/**
 * Created by andrew on 29.07.15.
 */
public class ChildItem implements Serializable{

    private Child child;
    private boolean flag;

    public ChildItem(Child child, boolean flag){
        setChild(child);
        setFlag(flag);
    }

    public ChildItem(Child child){
        this(child, false);
    }

    public boolean getFlag(){
        return flag;
    }

    public Child getChild(){
        return child;
    }

    public void setFlag(boolean boolValue){
        flag = boolValue;
    }

    public void setChild(Child child){
        this.child = child;
    }

    public void revertFlag(){
        flag = !flag;
    }
}

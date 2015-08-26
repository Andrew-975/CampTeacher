package com.andstepko.teacher.Logic;

import android.widget.Button;

import com.andstepko.teacher.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by andrew on 09.07.15.
 */
public class Squad implements Serializable{

    private static final int NO_NUMBER = 0;
    private static final String NO_NAME = "noName";

    private int number;
    private String name;
    private ArrayList<Child> children;
    private ChildSortType childSortType;

    // region Constructors
    public Squad() {
        this(new ArrayList<Child>());
    }

    public Squad(String squadString){
        String[] children = squadString.split("\n|\r\n");

        this.children = new ArrayList<Child>();
        for(String childString : children){
            addChild(new Child(childString));
        }

        childSortType = ChildSortType.NO;
    }

    public Squad(ArrayList<Child> children) {
        this(children, NO_NUMBER, NO_NAME);
    }

    public Squad(ArrayList<Child> children, int number, String name){
        this.children = (ArrayList<Child>)children.clone();
        childSortType = ChildSortType.NO;
        this.number = number;
        this.name = name;
    }
    // endregion Constructors

    private static Comparator<Child> getComparator(ChildSortType childSortType){
        switch (childSortType) {
            case NAME:
                return new ChildNameComparator();
            case SURNAME:
                return new ChildSurnameComparator();
            case ROOM:
                return new ChildRoomComparator();
            case AGE:
                return new ChildAgeComparator();
            case GROUP:
                return new ChildGroupComparator();
        }
        return null;
    }

    public ChildSortType getChildSortType(){
        return childSortType;
    }

    public ArrayList<Child> getChildren(){
        return children;
    }

    public void setChildren(ArrayList<Child> children){
        this.children = children;
    }

    public void addChild(Child child){
        children.add(child);
    }

    public void removeChild(Child child){
        children.remove(child);
    }

    //region sort
    public void sortByCurrentComparator(){
        sort(childSortType);
    }

    public void sort(ChildSortType childSortType){
        Comparator<Child> comparator = getComparator(childSortType);
        sort(comparator);
        this.childSortType = childSortType;
    }

    private void sort(Comparator<Child> comparator){
        if(comparator != null){
            Collections.sort(children, comparator);
        }
    }

    public void sortByName(){
        Collections.sort(children, new ChildNameComparator());
        childSortType = ChildSortType.NAME;
    }

    public void sortBySurname(){
        Collections.sort(children, new ChildSurnameComparator());
        childSortType = ChildSortType.SURNAME;
    }

    public void sortByRoom(){
        Collections.sort(children, new ChildRoomComparator());
        childSortType = ChildSortType.ROOM;
    }

    public void sortByAge(){
        Collections.sort(children, new ChildAgeComparator());
        childSortType = ChildSortType.AGE;
    }

    public void sortByGroup(){
        Collections.sort(children, new ChildGroupComparator());
        childSortType = ChildSortType.GROUP;
    }
    //endregion sort
}

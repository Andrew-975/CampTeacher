package com.andstepko.teacher.Logic;

import java.util.Comparator;

/**
 * Created by andrew on 29.07.15.
 */
public class ChildRoomComparator implements Comparator<Child> {
    @Override
    public int compare(Child child, Child anotherChild) {
        return (int)((child.getRoom() - anotherChild.getRoom()) * 100);
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }
}

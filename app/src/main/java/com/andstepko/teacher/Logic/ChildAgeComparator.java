package com.andstepko.teacher.Logic;

import java.util.Comparator;

/**
 * Created by andrew on 29.07.15.
 */
public class ChildAgeComparator implements Comparator<Child> {
    @Override
    public int compare(Child child, Child anotherChild) {
        return child.getAge()- anotherChild.getAge();
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }
}

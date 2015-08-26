package com.andstepko.teacher.Logic;

import java.util.Comparator;

/**
 * Created by andrew on 03.08.15.
 */
public class ChildGroupComparator implements Comparator<Child> {
    @Override
    public int compare(Child child, Child anotherChild) {
        boolean group1 = child.getGroup();
        boolean group2 = anotherChild.getGroup();
        //return child.getGroup() - anotherChild.getGroup();
        if(group1){
            if(!group2)
                return 1;
            else
                return 0;
        }
        else{
            if(group2)
                return -1;
            else
                return 0;
        }
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }
}

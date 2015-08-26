package com.andstepko.teacher.Logic;

import android.view.Surface;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

import javax.xml.parsers.SAXParser;

/**
 * Created by andrew on 09.07.15.
 */
public class Child implements Serializable {

    public static final int NO_AGE = -1;
    public static final String NO_SURNAME = "noSurname";
    public static final String NO_SURNAME_SIGN = "*";
    public static final String NO_REMARKS = new String();
    public static final double NO_ROOM = 0;

    private static final String SEPARATOR = " ";
    private static final String EMPTY_STRING = "";

    private String name;
    private String surname;
    private int age;
    private double room;
    private boolean group;
    private String remarks;

    //region GetSet
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRemarks() {
        return remarks;
    }

    public boolean getGroup(){
        return group;
    }

    public void setGroup(boolean group){
        this.group = group;
    }

    public double getRoom() {
        return room;
    }

    private String getRoomString(){
        if(getRoom() == 0){
            return EMPTY_STRING;
        }
        return String.valueOf(getRoom());
    }

    public void setRoom(double room) {
        this.room = room;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //endregion GetSet

    public String getShortSurname(){
        StringBuffer result = new StringBuffer();

        for(int i = 0; i < 3; i++){
            if(surname.length() > i){
                result.append(surname.charAt(i));
            }
        }
        return result.toString();
    }

    public String getSurnameFirstLetter(){
        if(surname.length() > 0){
            return String.valueOf(surname.charAt(0));
        }
        return NO_SURNAME_SIGN;
    }

    //region Constructors
    public Child(){
        this(EMPTY_STRING, EMPTY_STRING, NO_AGE, 0, EMPTY_STRING);
    }

    public Child(String name, double room){
        this(name, NO_SURNAME, NO_AGE, room, NO_REMARKS);
    }

    public Child(String name, double room, String remarks){
        this(name, NO_SURNAME, NO_AGE, room, remarks);
    }

    public Child(String name, String surname, double room){
        this(name, surname, NO_AGE, room, NO_REMARKS);
    }

    public Child(String name, String surname, double room, String remarks){
        this(name, surname, NO_AGE, room, remarks);
    }

    public Child(String name, String surname, int age, double room){
        // No Remarks.
        this(name, surname, age, room, NO_REMARKS);
    }

    public Child(String name, String surname, int age, double room, String remarks) {
        this(name, surname, age, room, false, remarks);
    }

    public Child(String name, String surname, int age, double room, boolean group, String remarks) {
        setName(name);
        setSurname(surname);
        setAge(age);
        setRoom(room);
        setGroup(group);
        setRemarks(remarks);
    }

    public Child(String childString){
        if(childString.equals(EMPTY_STRING)){
            setName(EMPTY_STRING);
            return;
        }

        String[] words = childString.split(SEPARATOR);

        if(words.length == 1){
            // ForeignChild, got only name.
            setName(words[0]);
            setSurname(EMPTY_STRING);
            setAge(NO_AGE);
            setRoom(NO_ROOM);
            setRemarks(EMPTY_STRING);
        }
        else if(words.length == 2){
            // ForeignChild, got only name and surname.
            setName(words[0]);
            setSurname(words[1]);
            setAge(NO_AGE);
            setRoom(NO_ROOM);
            setRemarks(EMPTY_STRING);
        }
        else if(words.length == 4){
            // Only name, surname, age and room.
            setName(words[1]);
            setSurname(words[0]);
            setAge(Integer.valueOf(words[2]));
            setRoom(Double.valueOf(words[3]));
        }
        else{
            // Full.
            // Гаевой Александр 9 12.2 1 Ринит, сосуды
            StringBuffer remarks = new StringBuffer();
            int i = 5;

            while(i < words.length){
                remarks.append(words[i]);
                remarks.append(SEPARATOR);
                i++;
            }
            //this(words[1], words[0], Integer.valueOf(words[2]), Double.valueOf(words[3]), remarks.toString());
            setName(words[1]);
            setSurname(words[0]);
            setAge(Integer.valueOf(words[2]));
            setRoom(Double.valueOf(words[3]));
            // Group.
            if((Integer.valueOf(words[4]) == 1) || (Integer.valueOf(words[4]) == 0)){
                setGroup(false);
            }
            else {
                setGroup(true);
            }
            setRemarks(remarks.toString());
        }
    }
    //endregion Constructors

    @Override
    public String toString() {
        return name + SEPARATOR + surname + SEPARATOR + room
                + SEPARATOR + SEPARATOR + SEPARATOR
                + age + SEPARATOR + remarks;
    }

    public String toString(ChildSortType childSortType){
        switch (childSortType){
            case SURNAME: return surname + SEPARATOR + name + SEPARATOR + room + SEPARATOR + age;
            case ROOM: return room + SEPARATOR + name + SEPARATOR + surname + SEPARATOR + age;
            case AGE: return age + SEPARATOR + name + SEPARATOR + surname + SEPARATOR + room;
        }
        return toStringNameSurnameRoomAge();
    }

    public String toStringNameSurname(){
        return name + SEPARATOR + surname;
    }
    
    public String toStringNameSurnameRoom(){
        if(name == EMPTY_STRING){
            return EMPTY_STRING;
        }
        if(room == NO_ROOM){
            return name + SEPARATOR + surname;
        }
        return name + SEPARATOR + surname + SEPARATOR + room;
    }

    public String toStringNameSurnameRoomAge(){
        return name + SEPARATOR + surname + SEPARATOR + room
                + SEPARATOR + SEPARATOR + SEPARATOR
                + age;
    }

    @Override
    public boolean equals(Object o){
        if((o == null) || (this.getClass() != o.getClass())){
            return false;
        }

        Child otherChild = (Child)o;
        if((this.surname != otherChild.surname) || (this.name != otherChild.name)
                || (this.room != otherChild.room) || (this.age != otherChild.age)){
            return false;
        }

        return true;
    }

    public static boolean canBeChildString(String potentialChildString){
        String[] words = potentialChildString.split(SEPARATOR);

        if(words.length < 4){
            return false;
        }

        return true;
    }
}
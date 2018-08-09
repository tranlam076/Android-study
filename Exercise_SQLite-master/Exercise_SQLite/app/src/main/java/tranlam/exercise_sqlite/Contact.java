package tranlam.exercise_sqlite;

import java.io.Serializable;

/**
 * Created by LamTran on 10/20/2017.
 */

public class Contact implements Serializable {
    private int id;
    private String name;
    private String number;
    private String address;
    private String time;
    private String day;
    private String gender;

    public Contact(String name, String number,String address,String gender,String day,String time) {
        this.name = name;
        this.number = number;
        this.address = address;
        this.gender = gender;
        this.day = day;
        this.time = time;
    }

    public Contact(int id, String name, String number,String address,String gender,String day,String time) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.address = address;
        this.gender = gender;
        this.day = day;
        this.time = time;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}

package com.example.assignment02;

public class Model {

    String name,age,clss,rollNum;

    public String getRollNum() {
        return rollNum;
    }

    public void setRollNum(String rollNum) {
        this.rollNum = rollNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getClss() {
        return clss;
    }

    public void setClss(String clss) {
        this.clss = clss;
    }

    public Model(String rollNum,String name, String age, String clss) {
        this.rollNum=rollNum;
        this.name = name;

        this.age = age;
        this.clss = clss;
    }
}

package com.example.assignment02;

public class Model {

    String name,age,clss;

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

    public Model(String name, String age, String clss) {
        this.name = name;
        this.age = age;
        this.clss = clss;
    }
}

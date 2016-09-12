package com.apsoft.scfb.bean;

/**
 * Created by Administrator on 2016/7/28.
 */
public class Team2DetailEntry {
    private int img;
    private String role;
    private String name;
    private String age;
    private String position;
    private String num;

    public Team2DetailEntry(int img, String role, String name, String age, String position, String num) {
        this.img = img;
        this.role = role;
        this.name = name;
        this.age = age;
        this.position = position;
        this.num = num;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}

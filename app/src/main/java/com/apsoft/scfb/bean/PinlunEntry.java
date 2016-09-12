package com.apsoft.scfb.bean;

/**
 * Created by LCCX on 2016/9/2.
 */
public class PinlunEntry {
    int index;
    String  content;

    public PinlunEntry(int index, String content) {
        this.index = index;
        this.content = content;
    }


    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

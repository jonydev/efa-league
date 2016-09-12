package com.apsoft.scfb.bean;

/**
 * Created by admin on 2016/8/10.
 */
public class DiscusisonEntry {
    String whichRace;
    String time;

    public String getWhichRace() {
        return whichRace;
    }

    public void setWhichRace(String whichRace) {
        this.whichRace = whichRace;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }



    public DiscusisonEntry(String whichRace, String time) {
        this.whichRace = whichRace;
        this.time = time;
    }
}

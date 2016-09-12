package com.apsoft.scfb.bean;

import java.util.List;

/**
 * Created by admin on 2016/8/11.
 */
public class TeamScheduleEntry {
    Integer code;
    List<GameSchedule> data;

    public static class GameSchedule{
        String id;
        String home_id;
        String away_id;
        String turn;
        String datetime;

        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
        }

        String place;
        String homescore;
        String awayscore;
        String office_id;
        String results;
        String home_team_name;
        String away_team_name;
        String home_team_image;
        String away_team_image;
        String flag;
        String home_team_upper;
        String home_team_lower;
        String away_team_upper;
        String away_team_lower;

        public String getHome_team_upper() {
            return home_team_upper;
        }

        public void setHome_team_upper(String home_team_upper) {
            this.home_team_upper = home_team_upper;
        }

        public String getHome_team_lower() {
            return home_team_lower;
        }

        public void setHome_team_lower(String home_team_lower) {
            this.home_team_lower = home_team_lower;
        }

        public String getAway_team_upper() {
            return away_team_upper;
        }

        public void setAway_team_upper(String away_team_upper) {
            this.away_team_upper = away_team_upper;
        }

        public String getAway_team_lower() {
            return away_team_lower;
        }

        public void setAway_team_lower(String away_team_lower) {
            this.away_team_lower = away_team_lower;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public String getHome_team_image() {
            return home_team_image;
        }

        public void setHome_team_image(String home_team_image) {
            this.home_team_image = home_team_image;
        }

        public String getAway_team_image() {
            return away_team_image;
        }

        public void setAway_team_image(String away_team_image) {
            this.away_team_image = away_team_image;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getHome_id() {
            return home_id;
        }

        public void setHome_id(String home_id) {
            this.home_id = home_id;
        }

        public String getAway_id() {
            return away_id;
        }

        public void setAway_id(String away_id) {
            this.away_id = away_id;
        }

        public String getTurn() {
            return turn;
        }

        public void setTurn(String turn) {
            this.turn = turn;
        }

        public String getDatetime() {
            return datetime;
        }

        public void setDatetime(String datetime) {
            this.datetime = datetime;
        }

        public String getHomescore() {
            return homescore;
        }

        public void setHomescore(String homescore) {
            this.homescore = homescore;
        }

        public String getAwayscore() {
            return awayscore;
        }

        public void setAwayscore(String awayscore) {
            this.awayscore = awayscore;
        }

        public String getOffice_id() {
            return office_id;
        }

        public void setOffice_id(String office_id) {
            this.office_id = office_id;
        }

        public String getResults() {
            return results;
        }

        public void setResults(String results) {
            this.results = results;
        }

        public String getHome_team_name() {
            return home_team_name;
        }

        public void setHome_team_name(String home_team_name) {
            this.home_team_name = home_team_name;
        }

        public String getAway_team_name() {
            return away_team_name;
        }

        public void setAway_team_name(String away_team_name) {
            this.away_team_name = away_team_name;
        }

    }

    public List<GameSchedule> getData() {
        return data;
    }

    public void setData(List<GameSchedule> data) {
        this.data = data;
    }
}

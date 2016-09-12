package com.apsoft.scfb.bean;

import java.util.List;

/**
 * Created by admin on 2016/8/10.
 */
public class MatchGameScheduleEntry {
    String id;
    String serial;
    String time;
    String name;
    String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    boolean status = false;

    List<GameSchedule> match;

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<GameSchedule> getMatch() {
        return match;
    }

    public void setMatch(List<GameSchedule> match) {
        this.match = match;
    }

    public static class GameSchedule{
        String id;
        String home_id;
        String away_id;
        String turn;
        String datetime;
        String flag;

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
        String home_team_upper;
        String home_team_lower;
        String away_team_upper;
        String away_team_lower;

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

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

        Umpire main_referee;
        Umpire left_umpire;
        Umpire right_umpire;

        public Umpire getMain_referee() {
            return main_referee;
        }

        public void setMain_referee(Umpire main_referee) {
            this.main_referee = main_referee;
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

        public Umpire getLeft_umpire() {
            return left_umpire;
        }

        public void setLeft_umpire(Umpire left_umpire) {
            this.left_umpire = left_umpire;
        }

        public Umpire getRight_umpire() {
            return right_umpire;
        }

        public void setRight_umpire(Umpire right_umpire) {
            this.right_umpire = right_umpire;
        }
    }

    public static class Umpire{
        String name;
        String type;
        String age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }
    }

}

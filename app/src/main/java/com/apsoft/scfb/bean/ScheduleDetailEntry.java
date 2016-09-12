package com.apsoft.scfb.bean;

import java.util.List;

/**
 * Created by admin on 2016/8/11.
 */
public class ScheduleDetailEntry {
    List<FirstAppearance>      home_base;
    List<LaterAppearance>      home_ex;
    List<FirstAppearance>      away_base;
    List<LaterAppearance>      away_ex;

    List<Goal>                  goals;
    List<HelpGoal>              help_goals;
    List<Card>                  red_cards;
    List<Card>                  yellow_cards;

    public List<FirstAppearance> getHome_bases() {
        return home_base;
    }

    public void setHome_bases(List<FirstAppearance> home_bases) {
        this.home_base = home_bases;
    }

    public List<LaterAppearance> getHome_ex() {
        return home_ex;
    }

    public void setHome_ex(List<LaterAppearance> home_ex) {
        this.home_ex = home_ex;
    }

    public List<FirstAppearance> getAway_base() {
        return away_base;
    }

    public void setAway_base(List<FirstAppearance> away_base) {
        this.away_base = away_base;
    }

    public List<LaterAppearance> getAway_ex() {
        return away_ex;
    }

    public void setAway_ex(List<LaterAppearance> away_ex) {
        this.away_ex = away_ex;
    }

    public List<Goal> getGoals() {
        return goals;
    }

    public void setGoals(List<Goal> goals) {
        this.goals = goals;
    }

    public List<HelpGoal> getHelp_goals() {
        return help_goals;
    }

    public void setHelp_goals(List<HelpGoal> help_goals) {
        this.help_goals = help_goals;
    }

    public List<Card> getRed_cards() {
        return red_cards;
    }

    public void setRed_cards(List<Card> red_cards) {
        this.red_cards = red_cards;
    }

    public List<Card> getYellow_cards() {
        return yellow_cards;
    }

    public void setYellow_cards(List<Card> yellow_cards) {
        this.yellow_cards = yellow_cards;
    }

    public static class FirstAppearance{
        String time;
        String member_id;
        String member_name;
        String team_name;

        public String getMember_id() {
            return member_id;
        }

        public void setMember_id(String member_id) {
            this.member_id = member_id;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getMember_name() {
            return member_name;
        }

        public void setMember_name(String member_name) {
            this.member_name = member_name;
        }

        public String getTeam_name() {
            return team_name;
        }

        public void setTeam_name(String team_name) {
            this.team_name = team_name;
        }
    }

    public static class LaterAppearance{
        String time;
        String member_id;
        String alternate_id;
        String member_name;
        String team_name;
        String alternate_name;
        boolean is_home = true;
        public LaterAppearance(){

        }

        public boolean is_home() {
            return is_home;
        }

        public void setIs_home(boolean is_home) {
            this.is_home = is_home;
        }

        public String getAlternate_name() {
            return alternate_name;
        }

        public void setAlternate_name(String alternate_name) {
            this.alternate_name = alternate_name;
        }

        public String getMember_id() {
            return member_id;
        }

        public void setMember_id(String member_id) {
            this.member_id = member_id;
        }

        public String getAlternate_id() {
            return alternate_id;
        }

        public void setAlternate_id(String alternate_id) {
            this.alternate_id = alternate_id;
        }

        public LaterAppearance(String time, String member_name, String team_name){
            this.time = time;
            this.member_name = member_name;
            this.team_name = team_name;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getMember_name() {
            return member_name;
        }

        public void setMember_name(String member_name) {
            this.member_name = member_name;
        }

        public String getTeam_name() {
            return team_name;
        }

        public void setTeam_name(String team_name) {
            this.team_name = team_name;
        }
    }

    public static class Goal{
        String member_name;
        String time;
        String team_name;
        String penalty;
        String stage;
        String member_id;
        String turn;

        public String getMember_name() {
            return member_name;
        }

        public void setMember_name(String member_name) {
            this.member_name = member_name;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getTeam_name() {
            return team_name;
        }

        public void setTeam_name(String team_name) {
            this.team_name = team_name;
        }

        public String getPenalty() {
            return penalty;
        }

        public void setPenalty(String penalty) {
            this.penalty = penalty;
        }

        public String getStage() {
            return stage;
        }

        public void setStage(String stage) {
            this.stage = stage;
        }

        public String getMember_id() {
            return member_id;
        }

        public void setMember_id(String member_id) {
            this.member_id = member_id;
        }

        public String getTurn() {
            return turn;
        }

        public void setTurn(String turn) {
            this.turn = turn;
        }
    }

    public static class HelpGoal{
        String member_name;
        String time;
        String team_name;
        String stage;
        String member_id;
        String turn;

        public String getMember_name() {
            return member_name;
        }

        public void setMember_name(String member_name) {
            this.member_name = member_name;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getTeam_name() {
            return team_name;
        }

        public void setTeam_name(String team_name) {
            this.team_name = team_name;
        }

        public String getStage() {
            return stage;
        }

        public void setStage(String stage) {
            this.stage = stage;
        }

        public String getMember_id() {
            return member_id;
        }

        public void setMember_id(String member_id) {
            this.member_id = member_id;
        }

        public String getTurn() {
            return turn;
        }

        public void setTurn(String turn) {
            this.turn = turn;
        }
    }

    public static class Card{
        String member_name;
        String time;
        String team_name;
        String stage;
        String member_id;
        String turn;

        public String getMember_name() {
            return member_name;
        }

        public void setMember_name(String member_name) {
            this.member_name = member_name;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getTeam_name() {
            return team_name;
        }

        public void setTeam_name(String team_name) {
            this.team_name = team_name;
        }

        public String getStage() {
            return stage;
        }

        public void setStage(String stage) {
            this.stage = stage;
        }

        public String getMember_id() {
            return member_id;
        }

        public void setMember_id(String member_id) {
            this.member_id = member_id;
        }

        public String getTurn() {
            return turn;
        }

        public void setTurn(String turn) {
            this.turn = turn;
        }
    }

}

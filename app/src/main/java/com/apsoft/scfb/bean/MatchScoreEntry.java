package com.apsoft.scfb.bean;

import java.util.List;

/**
 * Created by admin on 2016/8/11.
 */
public class MatchScoreEntry {
    Integer code;
    ScoreAll data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public ScoreAll getData() {
        return data;
    }

    public void setData(ScoreAll data) {
        this.data = data;
    }

    public static class ScoreAll{
        List<Integral> integrals;
        List<Goal> goals;
        List<HelpGoal> help_goals;
        List<Card> yellow_cards;
        List<Card> red_cards;
        List<RedYellowCard> ry_cards;

        public List<RedYellowCard> getRy_cards() {
            return ry_cards;
        }

        public void setRy_cards(List<RedYellowCard> ry_cards) {
            this.ry_cards = ry_cards;
        }

        public List<Integral> getIntegrals() {
            return integrals;
        }

        public void setIntegrals(List<Integral> integrals) {
            this.integrals = integrals;
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

        public List<Card> getYellow_cards() {
            return yellow_cards;
        }

        public void setYellow_cards(List<Card> yellow_cards) {
            this.yellow_cards = yellow_cards;
        }

        public List<Card> getRed_cards() {
            return red_cards;
        }

        public void setRed_cards(List<Card> red_cards) {
            this.red_cards = red_cards;
        }
    }

    public static class Integral{
        String team_name;
        String turn;
        String won;
        String even;
        String beaten;
        String goal;
        String point;
        String lost;
        String yellow;
        String red;
        String stage;
        String team_id;
        String office_id;

        public String getTeam_name() {
            return team_name;
        }

        public void setTeam_name(String team_name) {
            this.team_name = team_name;
        }

        public String getTurn() {
            return turn;
        }

        public void setTurn(String turn) {
            this.turn = turn;
        }

        public String getWon() {
            return won;
        }

        public void setWon(String won) {
            this.won = won;
        }

        public String getEven() {
            return even;
        }

        public void setEven(String even) {
            this.even = even;
        }

        public String getBeaten() {
            return beaten;
        }

        public void setBeaten(String beaten) {
            this.beaten = beaten;
        }

        public String getGoal() {
            return goal;
        }

        public void setGoal(String goal) {
            this.goal = goal;
        }

        public String getPoint() {
            return point;
        }

        public void setPoint(String point) {
            this.point = point;
        }

        public String getLost() {
            return lost;
        }

        public void setLost(String lost) {
            this.lost = lost;
        }

        public String getYellow() {
            return yellow;
        }

        public void setYellow(String yellow) {
            this.yellow = yellow;
        }

        public String getRed() {
            return red;
        }

        public void setRed(String red) {
            this.red = red;
        }

        public String getStage() {
            return stage;
        }

        public void setStage(String stage) {
            this.stage = stage;
        }

        public String getTeam_id() {
            return team_id;
        }

        public void setTeam_id(String team_id) {
            this.team_id = team_id;
        }

        public String getOffice_id() {
            return office_id;
        }

        public void setOffice_id(String office_id) {
            this.office_id = office_id;
        }
    }
    public static class Goal{
        String member_name;
        Integer num;
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

        public Integer getNum() {
            return num;
        }

        public void setNum(Integer num) {
            this.num = num;
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
        Integer num;
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

        public Integer getNum() {
            return num;
        }

        public void setNum(Integer num) {
            this.num = num;
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
        Integer num;
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

        public Integer getNum() {
            return num;
        }

        public void setNum(Integer num) {
            this.num = num;
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

    public static class RedYellowCard{
        String member_name;
        String yellow_number;
        String red_number;
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

        public String getYellow_number() {
            return yellow_number;
        }

        public void setYellow_number(String yellow_number) {
            this.yellow_number = yellow_number;
        }

        public String getRed_number() {
            return red_number;
        }

        public void setRed_number(String red_number) {
            this.red_number = red_number;
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

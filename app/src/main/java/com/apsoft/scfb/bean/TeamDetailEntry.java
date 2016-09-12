package com.apsoft.scfb.bean;

import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2016/8/10.
 */
public class TeamDetailEntry {
//前锋
//    public static String POSITION_VALUE_CF = "CF";
//    public static String POSITION_VALUE_CF = "FW";
//    public static String POSITION_VALUE_CF = "CF";
//    public static String POSITION_VALUE_CF = "CF";
    public static String getNameByPosition(String position){
        if(position==null){
            return "";
        }
        if(position.equalsIgnoreCase("CF") || position.equalsIgnoreCase("FW")){
            return "前锋";
        }else if(position.equalsIgnoreCase("CM") || position.equalsIgnoreCase("DM")){
            return "中场";
        }else if(position.equalsIgnoreCase("DC") || position.equalsIgnoreCase("RL")){
            return "后卫";
        }else if(position.equalsIgnoreCase("GK")){
            return "门将";
        }
        return "";
    }

    Team team;
    List<TeamMember> team_members;
    public static class Team{
        private String id;
        private String name;
        private String home;
        private String leader;
        private String winning;
        private String num;
        private String win;
        private String flat;
        private String loss;
        private String upper;
        private String lower;
        String leader_membername;
        String captain_membername;
        private String datetime;
        private String captain;
        private String photo;
        private String content;
        Map<String, Integer> position_member;

        public String getLeaderShowName(){
            if(leader_membername!=null && leader_membername.length()>0){
                return leader_membername;
            }
            return leader;
        }

        public String getCaptainShowName(){
            if(captain_membername!=null && captain_membername.length()>0){
                return captain_membername;
            }
            return captain;
        }


        public String getLeader_membername() {
            return leader_membername;
        }

        public void setLeader_membername(String leader_membername) {
            this.leader_membername = leader_membername;
        }

        public String getCaptain_membername() {
            return captain_membername;
        }

        public void setCaptain_membername(String captain_membername) {
            this.captain_membername = captain_membername;
        }

        public String getUpper() {
            return upper;
        }

        public void setUpper(String upper) {
            this.upper = upper;
        }

        public String getLower() {
            return lower;
        }

        public void setLower(String lower) {
            this.lower = lower;
        }

        public String getDatetime() {
            return datetime;
        }

        public void setDatetime(String datetime) {
            this.datetime = datetime;
        }



        public Map<String, Integer> getPosition_member() {
            return position_member;
        }

        public void setPosition_member(Map<String, Integer> position_member) {
            this.position_member = position_member;
        }


        public String getWinning() {
            return winning;
        }

        public void setWinning(String winning) {
            this.winning = winning;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getWin() {
            return win;
        }

        public void setWin(String win) {
            this.win = win;
        }

        public String getFlat() {
            return flat;
        }

        public void setFlat(String flat) {
            this.flat = flat;
        }

        public String getLoss() {
            return loss;
        }

        public void setLoss(String loss) {
            this.loss = loss;
        }

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

        public String getHome() {
            return home;
        }

        public void setHome(String home) {
            this.home = home;
        }

        public String getLeader() {
            return leader;
        }

        public void setLeader(String leader) {
            this.leader = leader;
        }

        public String getCaptain() {
            return captain;
        }

        public void setCaptain(String captain) {
            this.captain = captain;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
    public static class TeamMember{
        private String id;
        private String name;
        private String age;
        private String photo;
        private String number;
        private String position;
        private String team_id;

        public String getTeam_id() {
            return team_id;
        }

        public void setTeam_id(String team_id) {
            this.team_id = team_id;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

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

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getDatetime() {
            return datetime;
        }

        public void setDatetime(String datetime) {
            this.datetime = datetime;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        private String datetime;
        private String content;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public List<TeamMember> getTeam_members() {
        return team_members;
    }

    public void setTeam_members(List<TeamMember> team_members) {
        this.team_members = team_members;
    }
}

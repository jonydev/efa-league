package com.apsoft.scfb.bean;

import java.util.List;

/**
 * Created by admin on 2016/8/11.
 */
public class RaceListEntry {

    /**
     * code : 200
     * data : {"yellow_cards":[{"member_name":"干锦华","turn":1,"num":"2","member_id":"5b69cc9879fd4537854b27aa0ea0a1af","team_name":"万科红郡FC","stage":"12"},{"member_name":"李鹏飞","turn":1,"num":"1","member_id":"48517ded97984cb4b7fcac4f4cab46db","team_name":"万科红郡FC","stage":"12"}],"red_cards":[{"member_name":"李鹏飞","turn":1,"num":"1","member_id":"48517ded97984cb4b7fcac4f4cab46db","team_name":"万科红郡FC","stage":"12"}],"help_goal":[],"goal":[{"goal":"1","member_name":"干锦华","penalty":0,"turn":1,"member_id":"5b69cc9879fd4537854b27aa0ea0a1af","team_name":"万科红郡FC","stage":"12"},{"goal":"1","member_name":"李鹏飞","penalty":0,"turn":1,"member_id":"48517ded97984cb4b7fcac4f4cab46db","team_name":"万科红郡FC","stage":"12"}]}
     */

    private int code;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * member_name : 干锦华
         * turn : 1
         * num : 2
         * member_id : 5b69cc9879fd4537854b27aa0ea0a1af
         * team_name : 万科红郡FC
         * stage : 12
         */

        private List<YellowCardsBean> yellow_cards;
        /**
         * member_name : 李鹏飞
         * turn : 1
         * num : 1
         * member_id : 48517ded97984cb4b7fcac4f4cab46db
         * team_name : 万科红郡FC
         * stage : 12
         */

        private List<RedCardsBean> red_cards;
        private List<?> help_goal;
        /**
         * goal : 1
         * member_name : 干锦华
         * penalty : 0.0
         * turn : 1
         * member_id : 5b69cc9879fd4537854b27aa0ea0a1af
         * team_name : 万科红郡FC
         * stage : 12
         */

        private List<GoalBean> goal;

        public List<YellowCardsBean> getYellow_cards() {
            return yellow_cards;
        }

        public void setYellow_cards(List<YellowCardsBean> yellow_cards) {
            this.yellow_cards = yellow_cards;
        }

        public List<RedCardsBean> getRed_cards() {
            return red_cards;
        }

        public void setRed_cards(List<RedCardsBean> red_cards) {
            this.red_cards = red_cards;
        }

        public List<?> getHelp_goal() {
            return help_goal;
        }

        public void setHelp_goal(List<?> help_goal) {
            this.help_goal = help_goal;
        }

        public List<GoalBean> getGoal() {
            return goal;
        }

        public void setGoal(List<GoalBean> goal) {
            this.goal = goal;
        }

        public static class YellowCardsBean {
            private String member_name;
            private int turn;
            private String num;
            private String member_id;
            private String team_name;
            private String stage;

            public String getMember_name() {
                return member_name;
            }

            public void setMember_name(String member_name) {
                this.member_name = member_name;
            }

            public int getTurn() {
                return turn;
            }

            public void setTurn(int turn) {
                this.turn = turn;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public String getMember_id() {
                return member_id;
            }

            public void setMember_id(String member_id) {
                this.member_id = member_id;
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
        }

        public static class RedCardsBean {
            private String member_name;
            private int turn;
            private String num;
            private String member_id;
            private String team_name;
            private String stage;

            public String getMember_name() {
                return member_name;
            }

            public void setMember_name(String member_name) {
                this.member_name = member_name;
            }

            public int getTurn() {
                return turn;
            }

            public void setTurn(int turn) {
                this.turn = turn;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public String getMember_id() {
                return member_id;
            }

            public void setMember_id(String member_id) {
                this.member_id = member_id;
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
        }

        public static class GoalBean {
            private String goal;
            private String member_name;
            private double penalty;
            private int turn;
            private String member_id;
            private String team_name;
            private String stage;

            public String getGoal() {
                return goal;
            }

            public void setGoal(String goal) {
                this.goal = goal;
            }

            public String getMember_name() {
                return member_name;
            }

            public void setMember_name(String member_name) {
                this.member_name = member_name;
            }

            public double getPenalty() {
                return penalty;
            }

            public void setPenalty(double penalty) {
                this.penalty = penalty;
            }

            public int getTurn() {
                return turn;
            }

            public void setTurn(int turn) {
                this.turn = turn;
            }

            public String getMember_id() {
                return member_id;
            }

            public void setMember_id(String member_id) {
                this.member_id = member_id;
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

    }
}

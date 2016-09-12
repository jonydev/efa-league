package com.apsoft.scfb.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/28.
 */
public class Team2VariousEntry {

    private DatabeanBean databean;

    public DatabeanBean getDatabean() {
        return databean;
    }

    public void setDatabean(DatabeanBean databean) {
        this.databean = databean;
    }

    public static class DatabeanBean {
        /**
         * team_detail : {"average_age":"30","detail_list":[{"num_player":"1","positon":"门将","icon_player":"http://placehold.it/600/501fe1","name":"甘德丝","age":"31","role":"队长"},{"num_player":"4","positon":"前锋","icon_player":"http://placehold.it/600/35185e","name":"大卫","age":"31","role":"领队"},{"num_player":"6","positon":"门将","icon_player":"http://placehold.it/600/c96cad","name":"甘德丝","age":"31","role":"教练"},{"num_player":"3","positon":"门将","icon_player":"http://placehold.it/600/4d564d","name":"Jake","age":"31","role":"大前锋"}],"best_score":"季军","establish_time":"2012年","team_intro":"红郡足球俱乐部"}
         * record_player_num : 62
         * current_score : 2016春季  季军
         * team_icon : http://placehold.it/600/ea51da
         * team_name : 红郡
         * race_roll_num : 30
         * history_score : 2015冬季  季军
         */

        private List<ListArrayBean> listArray;

        public List<ListArrayBean> getListArray() {
            return listArray;
        }

        public void setListArray(List<ListArrayBean> listArray) {
            this.listArray = listArray;
        }

        public static class ListArrayBean {
            /**
             * average_age : 30
             * detail_list : [{"num_player":"1","positon":"门将","icon_player":"http://placehold.it/600/501fe1","name":"甘德丝","age":"31","role":"队长"},{"num_player":"4","positon":"前锋","icon_player":"http://placehold.it/600/35185e","name":"大卫","age":"31","role":"领队"},{"num_player":"6","positon":"门将","icon_player":"http://placehold.it/600/c96cad","name":"甘德丝","age":"31","role":"教练"},{"num_player":"3","positon":"门将","icon_player":"http://placehold.it/600/4d564d","name":"Jake","age":"31","role":"大前锋"}]
             * best_score : 季军
             * establish_time : 2012年
             * team_intro : 红郡足球俱乐部
             */

            private TeamDetailBean team_detail;
            private String record_player_num;
            private String current_score;
            private String team_icon;
            private String team_name;
            private String race_roll_num;
            private String history_score;

            public TeamDetailBean getTeam_detail() {
                return team_detail;
            }

            public void setTeam_detail(TeamDetailBean team_detail) {
                this.team_detail = team_detail;
            }

            public String getRecord_player_num() {
                return record_player_num;
            }

            public void setRecord_player_num(String record_player_num) {
                this.record_player_num = record_player_num;
            }

            public String getCurrent_score() {
                return current_score;
            }

            public void setCurrent_score(String current_score) {
                this.current_score = current_score;
            }

            public String getTeam_icon() {
                return team_icon;
            }

            public void setTeam_icon(String team_icon) {
                this.team_icon = team_icon;
            }

            public String getTeam_name() {
                return team_name;
            }

            public void setTeam_name(String team_name) {
                this.team_name = team_name;
            }

            public String getRace_roll_num() {
                return race_roll_num;
            }

            public void setRace_roll_num(String race_roll_num) {
                this.race_roll_num = race_roll_num;
            }

            public String getHistory_score() {
                return history_score;
            }

            public void setHistory_score(String history_score) {
                this.history_score = history_score;
            }

            public static class TeamDetailBean {
                private String average_age;
                private String best_score;
                private String establish_time;
                private String team_intro;
                /**
                 * num_player : 1
                 * positon : 门将
                 * icon_player : http://placehold.it/600/501fe1
                 * name : 甘德丝
                 * age : 31
                 * role : 队长
                 */

                private List<DetailListBean> detail_list;

                public String getAverage_age() {
                    return average_age;
                }

                public void setAverage_age(String average_age) {
                    this.average_age = average_age;
                }

                public String getBest_score() {
                    return best_score;
                }

                public void setBest_score(String best_score) {
                    this.best_score = best_score;
                }

                public String getEstablish_time() {
                    return establish_time;
                }

                public void setEstablish_time(String establish_time) {
                    this.establish_time = establish_time;
                }

                public String getTeam_intro() {
                    return team_intro;
                }

                public void setTeam_intro(String team_intro) {
                    this.team_intro = team_intro;
                }

                public List<DetailListBean> getDetail_list() {
                    return detail_list;
                }

                public void setDetail_list(List<DetailListBean> detail_list) {
                    this.detail_list = detail_list;
                }

                public static class DetailListBean {
                    private String num_player;
                    private String positon;
                    private String icon_player;
                    private String name;
                    private String age;
                    private String role;

                    public String getNum_player() {
                        return num_player;
                    }

                    public void setNum_player(String num_player) {
                        this.num_player = num_player;
                    }

                    public String getPositon() {
                        return positon;
                    }

                    public void setPositon(String positon) {
                        this.positon = positon;
                    }

                    public String getIcon_player() {
                        return icon_player;
                    }

                    public void setIcon_player(String icon_player) {
                        this.icon_player = icon_player;
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

                    public String getRole() {
                        return role;
                    }

                    public void setRole(String role) {
                        this.role = role;
                    }
                }
            }
        }
    }
}

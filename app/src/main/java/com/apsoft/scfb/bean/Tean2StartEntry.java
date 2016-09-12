package com.apsoft.scfb.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/8/3.
 */
public class Tean2StartEntry {

    public CompetitionZoneBean competition_zone;

    public CompetitionZoneBean getCompetition_zone() {
        return competition_zone;
    }

    public void setCompetition_zone(CompetitionZoneBean competition_zone) {
        this.competition_zone = competition_zone;
    }

    public static class CompetitionZoneBean {
        /**
         * zone : http://placehold.it/600/372c93
         */

        private List<DataBean> data;

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            private String id;
            private String zone;
            private Integer team_member_number;
            private Integer is_joined;
            private String name;
            private String home;
            private String leader;
            private String captain;
            private String photo;
            private String content;
            private String winning;

            public String getWinning() {
                return winning;
            }

            public void setWinning(String winning) {
                this.winning = winning;
            }

            public String getZone() {
                return zone;
            }

            public Integer getTeam_member_number() {
                return team_member_number;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public void setTeam_member_number(Integer team_member_number) {
                this.team_member_number = team_member_number;
            }

            public Integer getIs_joined() {
                return is_joined;
            }

            public void setIs_joined(Integer is_joined) {
                this.is_joined = is_joined;
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

            public void setZone(String zone) {
                this.zone = zone;
            }
        }
    }
}

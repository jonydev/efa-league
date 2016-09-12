package com.apsoft.scfb.bean;

/**
 * Created by LCCX on 2016/8/28.
 */
public class CodeBean {
   int code;

    dataBean data;

    public CodeBean(int code) {
        this.code = code;
    }

    public CodeBean() {
    }

    public dataBean getData() {
        return data;
    }

    public void setData(dataBean data) {
        this.data = data;
    }

    public static class dataBean{
        private String  isTeamLeader;
        private String  id;


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIsTeamLeader() {
            return isTeamLeader;
        }

        public void setIsTeamLeader(String isTeamLeader) {
            this.isTeamLeader = isTeamLeader;
        }

        public dataBean() {
        }
    }




    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}

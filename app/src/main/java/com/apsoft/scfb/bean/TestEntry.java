package com.apsoft.scfb.bean;

import java.util.List;

/**
 * Created by admin on 2016/8/9.
 */
public class TestEntry {

    /**
     * code : 200
     * data : [{"sort":"10","update_date":"2013-05-27 08:00:00","code":"100000","create_date":"2013-05-27 08:00:00","name":"中国","parent_ids":"0,","create_by":"1","parent_id":"0","remarks":null,"del_flag":"0","update_by":"1","type":"1","id":"1"},{"sort":"30","update_date":"2015-09-06 12:59:53","code":"430000","create_date":"2015-09-06 12:59:53","name":"湖北省","parent_ids":"0,1,","create_by":"1","parent_id":"1","remarks":"","del_flag":"0","update_by":"1","type":"2","id":"1a8ba89faa2a468380897b2a446b9c0a"},{"sort":"30","update_date":"2015-09-06 13:08:59","code":"430001","create_date":"2015-09-06 13:08:59","name":"武汉市","parent_ids":"0,1,1a8ba89faa2a468380897b2a446b9c0a,","create_by":"1","parent_id":"1a8ba89faa2a468380897b2a446b9c0a","remarks":"","del_flag":"0","update_by":"1","type":"3","id":"26d1992e05bf4d2e86fc3fa3495fd3c6"},{"sort":"30","update_date":"2016-07-25 00:23:42","code":"420001","create_date":"2016-06-24 11:00:16","name":"光谷","parent_ids":"0,1,1a8ba89faa2a468380897b2a446b9c0a,26d1992e05bf4d2e86fc3fa3495fd3c6,","create_by":"1","parent_id":"26d1992e05bf4d2e86fc3fa3495fd3c6","remarks":"","del_flag":"0","update_by":"1","type":"4","id":"2a77aad75c274f4ebb7d986648ebfe2d"},{"sort":"30","update_date":"2016-07-25 00:23:49","code":"420002","create_date":"2016-07-25 00:09:53","name":"武昌","parent_ids":"0,1,1a8ba89faa2a468380897b2a446b9c0a,26d1992e05bf4d2e86fc3fa3495fd3c6,","create_by":"1","parent_id":"26d1992e05bf4d2e86fc3fa3495fd3c6","remarks":"","del_flag":"0","update_by":"1","type":"4","id":"776fc0f8eab845b095460aaae0125229"}]
     */

    private int code;
    /**
     * sort : 10
     * update_date : 2013-05-27 08:00:00
     * code : 100000
     * create_date : 2013-05-27 08:00:00
     * name : 中国
     * parent_ids : 0,
     * create_by : 1
     * parent_id : 0
     * remarks : null
     * del_flag : 0
     * update_by : 1
     * type : 1
     * id : 1
     */

    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String sort;
        private String update_date;
        private String code;
        private String create_date;
        private String name;
        private String parent_ids;
        private String create_by;
        private String parent_id;
        private Object remarks;
        private String del_flag;
        private String update_by;
        private String type;
        private String id;

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getUpdate_date() {
            return update_date;
        }

        public void setUpdate_date(String update_date) {
            this.update_date = update_date;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getCreate_date() {
            return create_date;
        }

        public void setCreate_date(String create_date) {
            this.create_date = create_date;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getParent_ids() {
            return parent_ids;
        }

        public void setParent_ids(String parent_ids) {
            this.parent_ids = parent_ids;
        }

        public String getCreate_by() {
            return create_by;
        }

        public void setCreate_by(String create_by) {
            this.create_by = create_by;
        }

        public String getParent_id() {
            return parent_id;
        }

        public void setParent_id(String parent_id) {
            this.parent_id = parent_id;
        }

        public Object getRemarks() {
            return remarks;
        }

        public void setRemarks(Object remarks) {
            this.remarks = remarks;
        }

        public String getDel_flag() {
            return del_flag;
        }

        public void setDel_flag(String del_flag) {
            this.del_flag = del_flag;
        }

        public String getUpdate_by() {
            return update_by;
        }

        public void setUpdate_by(String update_by) {
            this.update_by = update_by;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}

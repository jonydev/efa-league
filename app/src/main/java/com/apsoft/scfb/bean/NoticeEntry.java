package com.apsoft.scfb.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/27.
 */
public class NoticeEntry {

    /**
     * data : 红郡和干德
     * time : 2016-7-23
     * id : 1
     */
    private Integer code;
    private List<NoticedataBean> data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<NoticedataBean> getData() {
        return data;
    }

    public void setData(List<NoticedataBean> data) {
        this.data = data;
    }

    public static class NoticedataBean {
        private String content;
        private String date;
        private String id;
        private String image;
        private String link;
        private boolean isChecked;

        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}

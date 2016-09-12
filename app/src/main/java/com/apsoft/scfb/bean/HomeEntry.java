package com.apsoft.scfb.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/25.
 */
public class HomeEntry {

    /**
     * image : http://placehold.it/600/d32776
     */

    private List<SliderBean> slider_news_images;
    /**
     * image : http://placehold.it/600/d32776
     */

    private List<ListBean> office_news_list;

    public List<SliderBean> getSlider_news_images() {
        return slider_news_images;
    }

    public void setSlider_news_images(List<SliderBean> slider_news_images) {
        this.slider_news_images = slider_news_images;
    }

    public List<ListBean> getOffice_news_list() {
        return office_news_list;
    }

    public void setOffice_news_list(List<ListBean> office_news_list) {
        this.office_news_list = office_news_list;
    }

    public List<SliderBean> getSlider() {
        return slider_news_images;
    }

    public void setSlider(List<SliderBean> slider) {
        this.slider_news_images = slider;
    }

    public List<ListBean> getList() {
        return office_news_list;
    }

    public void setList(List<ListBean> list) {
        this.office_news_list = list;
    }

    public static class SliderBean {
        private String image;
        private String name;
        private String link;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }

    public static class ListBean {
        private String image;
        private String id;
        private String name;
        private String rule;
        private String caption;
        private String is_default;

        public String getIs_default() {
            return is_default;
        }

        public void setIs_default(String is_default) {
            this.is_default = is_default;
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

        public String getRule() {
            return rule;
        }

        public void setRule(String rule) {
            this.rule = rule;
        }

        public String getCaption() {
            return caption;
        }

        public void setCaption(String caption) {
            this.caption = caption;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}

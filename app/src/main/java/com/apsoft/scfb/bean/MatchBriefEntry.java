package com.apsoft.scfb.bean;

/**
 * Created by Administrator on 2016/8/2.
 */
public class MatchBriefEntry {


    /**
     * rule_intro : 规则介绍大家都不能犯规规则介绍大家都不能犯规规则介绍大家都不能犯规规则介绍大家都不能犯规规则介绍大家都不能犯规规则介绍大家都不能犯规规则介绍大家都不能犯规规则介绍大家都不能犯规
     * race_brife : 赛事简介的内容是赛事简介的内容是赛事简介的内容是赛事简介的内容是赛事简介的内容是赛事简介的内容是赛事简介的内容是赛事简介的内容是赛事简介的内容是
     * icon_head : http://placehold.it/600/95acce
     * judge_intro : 裁判员介绍的主要是这个人裁判员介绍的裁判员介绍的裁判员介绍的裁判员介绍的裁判员介绍的裁判员介绍的裁判员介绍的裁判员介绍的裁判员介绍的裁判员介绍的裁判员介绍的
     */

    private BrifeDataBean brife_data;

    public BrifeDataBean getBrife_data() {
        return brife_data;
    }

    public void setBrife_data(BrifeDataBean brife_data) {
        this.brife_data = brife_data;
    }

    public static class BrifeDataBean {
        private String rule_intro;
        private String race_brife;
        private String icon_head;
        private String judge_intro;

        public String getRule_intro() {
            return rule_intro;
        }

        public void setRule_intro(String rule_intro) {
            this.rule_intro = rule_intro;
        }

        public String getRace_brife() {
            return race_brife;
        }

        public void setRace_brife(String race_brife) {
            this.race_brife = race_brife;
        }

        public String getIcon_head() {
            return icon_head;
        }

        public void setIcon_head(String icon_head) {
            this.icon_head = icon_head;
        }

        public String getJudge_intro() {
            return judge_intro;
        }

        public void setJudge_intro(String judge_intro) {
            this.judge_intro = judge_intro;
        }
    }
}

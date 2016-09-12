package com.apsoft.scfb.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/2.
 */
public class MatchScheduleEntry {

    private GamescheduleDataBean gameschedule_data;

    public GamescheduleDataBean getGameschedule_data() {
        return gameschedule_data;
    }

    public void setGameschedule_data(GamescheduleDataBean gameschedule_data) {
        this.gameschedule_data = gameschedule_data;
    }

    public static class GamescheduleDataBean {
        /**
         * whetherGame : true
         * time_group : 2016-8-1
         * title_group : 第一轮
         */

        private List<GroupDataBean> group_data;
        /**
         * assit : {"right_assit":{"right_assit_name":[{"name":"西朗读"},{"name":"西朗读"}]},"left_assit":{"left_assit_name":[{"name":"西朗读"},{"name":"西朗读"}]}}
         * judge_oanel : {"second_judge":"伊尔马托夫，考绍伊","primary_judge":"里佐利","fourth_officious":"斯塔克"}
         * goal : {"left_goal":{"left_goal_name":[{"name":"大伟"},{"name":"大伟"}],"left_goal_score":[{"score":"55"},{"score":"55"}]},"right_goal":{"right_goal_name":[{"name":"西朗读"}],"right_goal_score":[{"score":"55"}]}}
         * ratingbar : {"race_control":"3","fair_judge":"4","exact_judge":"5"}
         * yellow_card : {"right_yellow_card":{"right_yellow_card_num":[{"num":"33"},{"num":"33"}],"right_yellow_card_name":[{"name":"西朗读"},{"name":"大伟"}]},"left_yellow_card":{"left_yellow_card_num":[{"num":"44"},{"num":"44"}],"left_yellow_card_name":[{"name":"大伟"},{"name":"大伟"}]}}
         * red_card : {"left_red_card":{"left_red_card_name":[{"name":"西朗读"}],"left_red_card_num":[{"num":"88"}]},"right_red_card":{"right_red_card_num":[{"num":"77"}],"right_red_card_name":[{"name":"大伟"}]}}
         * right_team_cloth : http://placehold.it/600/66b7d2
         * left_team_name : 红郡1
         * ball_park : 一号场
         * right_team_name : 强势疾风1
         * right_team_glag : http://placehold.it/600/197d29
         * left_team_cloth : http://placehold.it/600/810b14
         * detail_time : 09:00
         * contrastScore :
         * left_team_flag : http://placehold.it/600/1ee8a4
         */

        private List<FourchilddataBean> fourchilddata;
        /**
         * red_card : {"left_red_card":{"left_red_card_num":[{"num":"88"}],"left_red_card_name":[{"name":"西朗读"}]},"right_red_card":{"right_red_card_num":[{"num":"77"}],"right_red_card_name":[{"name":"大伟"}]}}
         * judge_oanel : {"second_judge":"伊尔马托夫，考绍伊","primary_judge":"里佐利","fourth_officious":"斯塔克"}
         * yellow_card : {"left_yellow_card":{"left_yellow_card_name":[{"name":"大伟"},{"name":"大伟"}],"left_yellow_card_num":[{"num":"44"},{"num":"44"}]},"right_yellow_card":{"right_yellow_card_num":[{"num":"33"},{"num":"33"}],"right_yellow_card_name":[{"name":"西朗读"},{"name":"大伟"}]}}
         * assit : {"left_assit":{"left_assit_name":[{"name":"西朗读"},{"name":"西朗读"}]},"right_assit":{"right_assit_name":[{"name":"西朗读"},{"name":"西朗读"}]}}
         * goal : {"left_goal":{"left_goal_score":[{"score":"55"},{"score":"55"}],"left_goal_name":[{"name":"大伟"},{"name":"大伟"}]},"right_goal":{"right_goal_name":[{"name":"西朗读"}],"right_goal_score":[{"score":"55"}]}}
         * ratingbar : {"fair_judge":"4","exact_judge":"5","race_control":"3"}
         * ball_park : 一号场
         * right_team_name : 强势疾风1
         * left_team_flag : http://placehold.it/600/1ee8a4
         * left_team_cloth : http://placehold.it/600/810b14
         * contrastScore :
         * right_team_cloth : http://placehold.it/600/66b7d2
         * detail_time : 09:00
         * right_team_glag : http://placehold.it/600/197d29
         * left_team_name : 红郡1
         */

        private List<ThirdchilddataBean> thirdchilddata;
        /**
         * yellow_card : {"right_yellow_card":{"right_yellow_card_num":[{"num":"33"},{"num":"33"}],"right_yellow_card_name":[{"name":"西朗读"},{"name":"大伟"}]},"left_yellow_card":{"left_yellow_card_num":[{"num":"44"},{"num":"44"}],"left_yellow_card_name":[{"name":"大伟"},{"name":"大伟"}]}}
         * goal : {"right_goal":{"right_goal_score":[{"score":"55"}],"right_goal_name":[{"name":"西朗读"}]},"left_goal":{"left_goal_name":[{"name":"大伟"},{"name":"大伟"}],"left_goal_score":[{"score":"55"},{"score":"55"}]}}
         * assit : {"left_assit":{"left_assit_name":[{"name":"西朗读"},{"name":"西朗读"}]},"right_assit":{"right_assit_name":[{"name":"西朗读"},{"name":"西朗读"}]}}
         * judge_oanel : {"fourth_officious":"斯塔克","second_judge":"伊尔马托夫，考绍伊","primary_judge":"里佐利"}
         * red_card : {"left_red_card":{"left_red_card_num":[{"num":"88"}],"left_red_card_name":[{"name":"西朗读"}]},"right_red_card":{"right_red_card_name":[{"name":"大伟"}],"right_red_card_num":[{"num":"77"}]}}
         * ratingbar : {"exact_judge":"5","fair_judge":"4","race_control":"3"}
         * ball_park : 一号场
         * left_team_name : 红郡1
         * right_team_name : 强势疾风1
         * detail_time : 09:00
         * left_team_cloth : http://placehold.it/600/810b14
         * contrastScore :
         * left_team_flag : http://placehold.it/600/1ee8a4
         * right_team_cloth : http://placehold.it/600/66b7d2
         * right_team_glag : http://placehold.it/600/197d29
         */

        private List<SecondchilddataBean> secondchilddata;
        /**
         * goal : {"left_goal":{"left_goal_score":[{"score":"55"},{"score":"55"}],"left_goal_name":[{"name":"大伟"},{"name":"大伟"}]},"right_goal":{"right_goal_name":[{"name":"西朗读"}],"right_goal_score":[{"score":"55"}]}}
         * assit : {"left_assit":{"left_assit_name":[{"name":"西朗读"},{"name":"西朗读"}]},"right_assit":{"right_assit_name":[{"name":"西朗读"},{"name":"西朗读"}]}}
         * ratingbar : {"fair_judge":"4","exact_judge":"5","race_control":"3"}
         * yellow_card : {"right_yellow_card":{"right_yellow_card_name":[{"name":"西朗读"},{"name":"大伟"}],"right_yellow_card_num":[{"num":"33"},{"num":"33"}]},"left_yellow_card":{"left_yellow_card_num":[{"num":"44"},{"num":"44"}],"left_yellow_card_name":[{"name":"大伟"},{"name":"大伟"}]}}
         * red_card : {"left_red_card":{"left_red_card_name":[{"name":"西朗读"}],"left_red_card_num":[{"num":"88"}]},"right_red_card":{"right_red_card_name":[{"name":"大伟"}],"right_red_card_num":[{"num":"77"}]}}
         * judge_oanel : {"primary_judge":"里佐利","fourth_officious":"斯塔克","second_judge":"伊尔马托夫，考绍伊"}
         * detail_time : 09:00
         * left_team_flag : http://placehold.it/600/1ee8a4
         * right_team_cloth : http://placehold.it/600/66b7d2
         * left_team_cloth : http://placehold.it/600/810b14
         * right_team_name : 强势疾风1
         * ball_park : 一号场
         * right_team_glag : http://placehold.it/600/197d29
         * contrastScore : 2:3
         * left_team_name : 红郡1
         */

        private List<FirstchilddataBean> firstchilddata;

        public List<GroupDataBean> getGroup_data() {
            return group_data;
        }

        public void setGroup_data(List<GroupDataBean> group_data) {
            this.group_data = group_data;
        }

        public List<FourchilddataBean> getFourchilddata() {
            return fourchilddata;
        }

        public void setFourchilddata(List<FourchilddataBean> fourchilddata) {
            this.fourchilddata = fourchilddata;
        }

        public List<ThirdchilddataBean> getThirdchilddata() {
            return thirdchilddata;
        }

        public void setThirdchilddata(List<ThirdchilddataBean> thirdchilddata) {
            this.thirdchilddata = thirdchilddata;
        }

        public List<SecondchilddataBean> getSecondchilddata() {
            return secondchilddata;
        }

        public void setSecondchilddata(List<SecondchilddataBean> secondchilddata) {
            this.secondchilddata = secondchilddata;
        }

        public List<FirstchilddataBean> getFirstchilddata() {
            return firstchilddata;
        }

        public void setFirstchilddata(List<FirstchilddataBean> firstchilddata) {
            this.firstchilddata = firstchilddata;
        }

        public static class GroupDataBean {
            private String whetherGame;
            private String time_group;
            private String title_group;

            public String getWhetherGame() {
                return whetherGame;
            }

            public void setWhetherGame(String whetherGame) {
                this.whetherGame = whetherGame;
            }

            public String getTime_group() {
                return time_group;
            }

            public void setTime_group(String time_group) {
                this.time_group = time_group;
            }

            public String getTitle_group() {
                return title_group;
            }

            public void setTitle_group(String title_group) {
                this.title_group = title_group;
            }
        }

        public static class FourchilddataBean {
            /**
             * right_assit : {"right_assit_name":[{"name":"西朗读"},{"name":"西朗读"}]}
             * left_assit : {"left_assit_name":[{"name":"西朗读"},{"name":"西朗读"}]}
             */

            private AssitBean assit;
            /**
             * second_judge : 伊尔马托夫，考绍伊
             * primary_judge : 里佐利
             * fourth_officious : 斯塔克
             */

            private JudgeOanelBean judge_oanel;
            /**
             * left_goal : {"left_goal_name":[{"name":"大伟"},{"name":"大伟"}],"left_goal_score":[{"score":"55"},{"score":"55"}]}
             * right_goal : {"right_goal_name":[{"name":"西朗读"}],"right_goal_score":[{"score":"55"}]}
             */

            private GoalBean goal;
            /**
             * race_control : 3
             * fair_judge : 4
             * exact_judge : 5
             */

            private RatingbarBean ratingbar;
            /**
             * right_yellow_card : {"right_yellow_card_num":[{"num":"33"},{"num":"33"}],"right_yellow_card_name":[{"name":"西朗读"},{"name":"大伟"}]}
             * left_yellow_card : {"left_yellow_card_num":[{"num":"44"},{"num":"44"}],"left_yellow_card_name":[{"name":"大伟"},{"name":"大伟"}]}
             */

            private YellowCardBean yellow_card;
            /**
             * left_red_card : {"left_red_card_name":[{"name":"西朗读"}],"left_red_card_num":[{"num":"88"}]}
             * right_red_card : {"right_red_card_num":[{"num":"77"}],"right_red_card_name":[{"name":"大伟"}]}
             */

            private RedCardBean red_card;
            private String right_team_cloth;
            private String left_team_name;
            private String ball_park;
            private String right_team_name;
            private String right_team_glag;
            private String left_team_cloth;
            private String detail_time;
            private String contrastScore;
            private String left_team_flag;

            public AssitBean getAssit() {
                return assit;
            }

            public void setAssit(AssitBean assit) {
                this.assit = assit;
            }

            public JudgeOanelBean getJudge_oanel() {
                return judge_oanel;
            }

            public void setJudge_oanel(JudgeOanelBean judge_oanel) {
                this.judge_oanel = judge_oanel;
            }

            public GoalBean getGoal() {
                return goal;
            }

            public void setGoal(GoalBean goal) {
                this.goal = goal;
            }

            public RatingbarBean getRatingbar() {
                return ratingbar;
            }

            public void setRatingbar(RatingbarBean ratingbar) {
                this.ratingbar = ratingbar;
            }

            public YellowCardBean getYellow_card() {
                return yellow_card;
            }

            public void setYellow_card(YellowCardBean yellow_card) {
                this.yellow_card = yellow_card;
            }

            public RedCardBean getRed_card() {
                return red_card;
            }

            public void setRed_card(RedCardBean red_card) {
                this.red_card = red_card;
            }

            public String getRight_team_cloth() {
                return right_team_cloth;
            }

            public void setRight_team_cloth(String right_team_cloth) {
                this.right_team_cloth = right_team_cloth;
            }

            public String getLeft_team_name() {
                return left_team_name;
            }

            public void setLeft_team_name(String left_team_name) {
                this.left_team_name = left_team_name;
            }

            public String getBall_park() {
                return ball_park;
            }

            public void setBall_park(String ball_park) {
                this.ball_park = ball_park;
            }

            public String getRight_team_name() {
                return right_team_name;
            }

            public void setRight_team_name(String right_team_name) {
                this.right_team_name = right_team_name;
            }

            public String getRight_team_glag() {
                return right_team_glag;
            }

            public void setRight_team_glag(String right_team_glag) {
                this.right_team_glag = right_team_glag;
            }

            public String getLeft_team_cloth() {
                return left_team_cloth;
            }

            public void setLeft_team_cloth(String left_team_cloth) {
                this.left_team_cloth = left_team_cloth;
            }

            public String getDetail_time() {
                return detail_time;
            }

            public void setDetail_time(String detail_time) {
                this.detail_time = detail_time;
            }

            public String getContrastScore() {
                return contrastScore;
            }

            public void setContrastScore(String contrastScore) {
                this.contrastScore = contrastScore;
            }

            public String getLeft_team_flag() {
                return left_team_flag;
            }

            public void setLeft_team_flag(String left_team_flag) {
                this.left_team_flag = left_team_flag;
            }

            public static class AssitBean {
                private RightAssitBean right_assit;
                private LeftAssitBean left_assit;

                public RightAssitBean getRight_assit() {
                    return right_assit;
                }

                public void setRight_assit(RightAssitBean right_assit) {
                    this.right_assit = right_assit;
                }

                public LeftAssitBean getLeft_assit() {
                    return left_assit;
                }

                public void setLeft_assit(LeftAssitBean left_assit) {
                    this.left_assit = left_assit;
                }

                public static class RightAssitBean {
                    /**
                     * name : 西朗读
                     */

                    private List<RightAssitNameBean> right_assit_name;

                    public List<RightAssitNameBean> getRight_assit_name() {
                        return right_assit_name;
                    }

                    public void setRight_assit_name(List<RightAssitNameBean> right_assit_name) {
                        this.right_assit_name = right_assit_name;
                    }

                    public static class RightAssitNameBean {
                        private String name;

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }
                    }
                }

                public static class LeftAssitBean {
                    /**
                     * name : 西朗读
                     */

                    private List<LeftAssitNameBean> left_assit_name;

                    public List<LeftAssitNameBean> getLeft_assit_name() {
                        return left_assit_name;
                    }

                    public void setLeft_assit_name(List<LeftAssitNameBean> left_assit_name) {
                        this.left_assit_name = left_assit_name;
                    }

                    public static class LeftAssitNameBean {
                        private String name;

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }
                    }
                }
            }

            public static class JudgeOanelBean {
                private String second_judge;
                private String primary_judge;
                private String fourth_officious;

                public String getSecond_judge() {
                    return second_judge;
                }

                public void setSecond_judge(String second_judge) {
                    this.second_judge = second_judge;
                }

                public String getPrimary_judge() {
                    return primary_judge;
                }

                public void setPrimary_judge(String primary_judge) {
                    this.primary_judge = primary_judge;
                }

                public String getFourth_officious() {
                    return fourth_officious;
                }

                public void setFourth_officious(String fourth_officious) {
                    this.fourth_officious = fourth_officious;
                }
            }

            public static class GoalBean {
                private LeftGoalBean left_goal;
                private RightGoalBean right_goal;

                public LeftGoalBean getLeft_goal() {
                    return left_goal;
                }

                public void setLeft_goal(LeftGoalBean left_goal) {
                    this.left_goal = left_goal;
                }

                public RightGoalBean getRight_goal() {
                    return right_goal;
                }

                public void setRight_goal(RightGoalBean right_goal) {
                    this.right_goal = right_goal;
                }

                public static class LeftGoalBean {
                    /**
                     * name : 大伟
                     */

                    private List<LeftGoalNameBean> left_goal_name;
                    /**
                     * score : 55
                     */

                    private List<LeftGoalScoreBean> left_goal_score;

                    public List<LeftGoalNameBean> getLeft_goal_name() {
                        return left_goal_name;
                    }

                    public void setLeft_goal_name(List<LeftGoalNameBean> left_goal_name) {
                        this.left_goal_name = left_goal_name;
                    }

                    public List<LeftGoalScoreBean> getLeft_goal_score() {
                        return left_goal_score;
                    }

                    public void setLeft_goal_score(List<LeftGoalScoreBean> left_goal_score) {
                        this.left_goal_score = left_goal_score;
                    }

                    public static class LeftGoalNameBean {
                        private String name;

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }
                    }

                    public static class LeftGoalScoreBean {
                        private String score;

                        public String getScore() {
                            return score;
                        }

                        public void setScore(String score) {
                            this.score = score;
                        }
                    }
                }

                public static class RightGoalBean {
                    /**
                     * name : 西朗读
                     */

                    private List<RightGoalNameBean> right_goal_name;
                    /**
                     * score : 55
                     */

                    private List<RightGoalScoreBean> right_goal_score;

                    public List<RightGoalNameBean> getRight_goal_name() {
                        return right_goal_name;
                    }

                    public void setRight_goal_name(List<RightGoalNameBean> right_goal_name) {
                        this.right_goal_name = right_goal_name;
                    }

                    public List<RightGoalScoreBean> getRight_goal_score() {
                        return right_goal_score;
                    }

                    public void setRight_goal_score(List<RightGoalScoreBean> right_goal_score) {
                        this.right_goal_score = right_goal_score;
                    }

                    public static class RightGoalNameBean {
                        private String name;

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }
                    }

                    public static class RightGoalScoreBean {
                        private String score;

                        public String getScore() {
                            return score;
                        }

                        public void setScore(String score) {
                            this.score = score;
                        }
                    }
                }
            }

            public static class RatingbarBean {
                private String race_control;
                private String fair_judge;
                private String exact_judge;

                public String getRace_control() {
                    return race_control;
                }

                public void setRace_control(String race_control) {
                    this.race_control = race_control;
                }

                public String getFair_judge() {
                    return fair_judge;
                }

                public void setFair_judge(String fair_judge) {
                    this.fair_judge = fair_judge;
                }

                public String getExact_judge() {
                    return exact_judge;
                }

                public void setExact_judge(String exact_judge) {
                    this.exact_judge = exact_judge;
                }
            }

            public static class YellowCardBean {
                private RightYellowCardBean right_yellow_card;
                private LeftYellowCardBean left_yellow_card;

                public RightYellowCardBean getRight_yellow_card() {
                    return right_yellow_card;
                }

                public void setRight_yellow_card(RightYellowCardBean right_yellow_card) {
                    this.right_yellow_card = right_yellow_card;
                }

                public LeftYellowCardBean getLeft_yellow_card() {
                    return left_yellow_card;
                }

                public void setLeft_yellow_card(LeftYellowCardBean left_yellow_card) {
                    this.left_yellow_card = left_yellow_card;
                }

                public static class RightYellowCardBean {
                    /**
                     * num : 33
                     */

                    private List<RightYellowCardNumBean> right_yellow_card_num;
                    /**
                     * name : 西朗读
                     */

                    private List<RightYellowCardNameBean> right_yellow_card_name;

                    public List<RightYellowCardNumBean> getRight_yellow_card_num() {
                        return right_yellow_card_num;
                    }

                    public void setRight_yellow_card_num(List<RightYellowCardNumBean> right_yellow_card_num) {
                        this.right_yellow_card_num = right_yellow_card_num;
                    }

                    public List<RightYellowCardNameBean> getRight_yellow_card_name() {
                        return right_yellow_card_name;
                    }

                    public void setRight_yellow_card_name(List<RightYellowCardNameBean> right_yellow_card_name) {
                        this.right_yellow_card_name = right_yellow_card_name;
                    }

                    public static class RightYellowCardNumBean {
                        private String num;

                        public String getNum() {
                            return num;
                        }

                        public void setNum(String num) {
                            this.num = num;
                        }
                    }

                    public static class RightYellowCardNameBean {
                        private String name;

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }
                    }
                }

                public static class LeftYellowCardBean {
                    /**
                     * num : 44
                     */

                    private List<LeftYellowCardNumBean> left_yellow_card_num;
                    /**
                     * name : 大伟
                     */

                    private List<LeftYellowCardNameBean> left_yellow_card_name;

                    public List<LeftYellowCardNumBean> getLeft_yellow_card_num() {
                        return left_yellow_card_num;
                    }

                    public void setLeft_yellow_card_num(List<LeftYellowCardNumBean> left_yellow_card_num) {
                        this.left_yellow_card_num = left_yellow_card_num;
                    }

                    public List<LeftYellowCardNameBean> getLeft_yellow_card_name() {
                        return left_yellow_card_name;
                    }

                    public void setLeft_yellow_card_name(List<LeftYellowCardNameBean> left_yellow_card_name) {
                        this.left_yellow_card_name = left_yellow_card_name;
                    }

                    public static class LeftYellowCardNumBean {
                        private String num;

                        public String getNum() {
                            return num;
                        }

                        public void setNum(String num) {
                            this.num = num;
                        }
                    }

                    public static class LeftYellowCardNameBean {
                        private String name;

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }
                    }
                }
            }

            public static class RedCardBean {
                private LeftRedCardBean left_red_card;
                private RightRedCardBean right_red_card;

                public LeftRedCardBean getLeft_red_card() {
                    return left_red_card;
                }

                public void setLeft_red_card(LeftRedCardBean left_red_card) {
                    this.left_red_card = left_red_card;
                }

                public RightRedCardBean getRight_red_card() {
                    return right_red_card;
                }

                public void setRight_red_card(RightRedCardBean right_red_card) {
                    this.right_red_card = right_red_card;
                }

                public static class LeftRedCardBean {
                    /**
                     * name : 西朗读
                     */

                    private List<LeftRedCardNameBean> left_red_card_name;
                    /**
                     * num : 88
                     */

                    private List<LeftRedCardNumBean> left_red_card_num;

                    public List<LeftRedCardNameBean> getLeft_red_card_name() {
                        return left_red_card_name;
                    }

                    public void setLeft_red_card_name(List<LeftRedCardNameBean> left_red_card_name) {
                        this.left_red_card_name = left_red_card_name;
                    }

                    public List<LeftRedCardNumBean> getLeft_red_card_num() {
                        return left_red_card_num;
                    }

                    public void setLeft_red_card_num(List<LeftRedCardNumBean> left_red_card_num) {
                        this.left_red_card_num = left_red_card_num;
                    }

                    public static class LeftRedCardNameBean {
                        private String name;

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }
                    }

                    public static class LeftRedCardNumBean {
                        private String num;

                        public String getNum() {
                            return num;
                        }

                        public void setNum(String num) {
                            this.num = num;
                        }
                    }
                }

                public static class RightRedCardBean {
                    /**
                     * num : 77
                     */

                    private List<RightRedCardNumBean> right_red_card_num;
                    /**
                     * name : 大伟
                     */

                    private List<RightRedCardNameBean> right_red_card_name;

                    public List<RightRedCardNumBean> getRight_red_card_num() {
                        return right_red_card_num;
                    }

                    public void setRight_red_card_num(List<RightRedCardNumBean> right_red_card_num) {
                        this.right_red_card_num = right_red_card_num;
                    }

                    public List<RightRedCardNameBean> getRight_red_card_name() {
                        return right_red_card_name;
                    }

                    public void setRight_red_card_name(List<RightRedCardNameBean> right_red_card_name) {
                        this.right_red_card_name = right_red_card_name;
                    }

                    public static class RightRedCardNumBean {
                        private String num;

                        public String getNum() {
                            return num;
                        }

                        public void setNum(String num) {
                            this.num = num;
                        }
                    }

                    public static class RightRedCardNameBean {
                        private String name;

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }
                    }
                }
            }
        }

        public static class ThirdchilddataBean {
            /**
             * left_red_card : {"left_red_card_num":[{"num":"88"}],"left_red_card_name":[{"name":"西朗读"}]}
             * right_red_card : {"right_red_card_num":[{"num":"77"}],"right_red_card_name":[{"name":"大伟"}]}
             */

            private RedCardBean red_card;
            /**
             * second_judge : 伊尔马托夫，考绍伊
             * primary_judge : 里佐利
             * fourth_officious : 斯塔克
             */

            private JudgeOanelBean judge_oanel;
            /**
             * left_yellow_card : {"left_yellow_card_name":[{"name":"大伟"},{"name":"大伟"}],"left_yellow_card_num":[{"num":"44"},{"num":"44"}]}
             * right_yellow_card : {"right_yellow_card_num":[{"num":"33"},{"num":"33"}],"right_yellow_card_name":[{"name":"西朗读"},{"name":"大伟"}]}
             */

            private YellowCardBean yellow_card;
            /**
             * left_assit : {"left_assit_name":[{"name":"西朗读"},{"name":"西朗读"}]}
             * right_assit : {"right_assit_name":[{"name":"西朗读"},{"name":"西朗读"}]}
             */

            private AssitBean assit;
            /**
             * left_goal : {"left_goal_score":[{"score":"55"},{"score":"55"}],"left_goal_name":[{"name":"大伟"},{"name":"大伟"}]}
             * right_goal : {"right_goal_name":[{"name":"西朗读"}],"right_goal_score":[{"score":"55"}]}
             */

            private GoalBean goal;
            /**
             * fair_judge : 4
             * exact_judge : 5
             * race_control : 3
             */

            private RatingbarBean ratingbar;
            private String ball_park;
            private String right_team_name;
            private String left_team_flag;
            private String left_team_cloth;
            private String contrastScore;
            private String right_team_cloth;
            private String detail_time;
            private String right_team_glag;
            private String left_team_name;

            public RedCardBean getRed_card() {
                return red_card;
            }

            public void setRed_card(RedCardBean red_card) {
                this.red_card = red_card;
            }

            public JudgeOanelBean getJudge_oanel() {
                return judge_oanel;
            }

            public void setJudge_oanel(JudgeOanelBean judge_oanel) {
                this.judge_oanel = judge_oanel;
            }

            public YellowCardBean getYellow_card() {
                return yellow_card;
            }

            public void setYellow_card(YellowCardBean yellow_card) {
                this.yellow_card = yellow_card;
            }

            public AssitBean getAssit() {
                return assit;
            }

            public void setAssit(AssitBean assit) {
                this.assit = assit;
            }

            public GoalBean getGoal() {
                return goal;
            }

            public void setGoal(GoalBean goal) {
                this.goal = goal;
            }

            public RatingbarBean getRatingbar() {
                return ratingbar;
            }

            public void setRatingbar(RatingbarBean ratingbar) {
                this.ratingbar = ratingbar;
            }

            public String getBall_park() {
                return ball_park;
            }

            public void setBall_park(String ball_park) {
                this.ball_park = ball_park;
            }

            public String getRight_team_name() {
                return right_team_name;
            }

            public void setRight_team_name(String right_team_name) {
                this.right_team_name = right_team_name;
            }

            public String getLeft_team_flag() {
                return left_team_flag;
            }

            public void setLeft_team_flag(String left_team_flag) {
                this.left_team_flag = left_team_flag;
            }

            public String getLeft_team_cloth() {
                return left_team_cloth;
            }

            public void setLeft_team_cloth(String left_team_cloth) {
                this.left_team_cloth = left_team_cloth;
            }

            public String getContrastScore() {
                return contrastScore;
            }

            public void setContrastScore(String contrastScore) {
                this.contrastScore = contrastScore;
            }

            public String getRight_team_cloth() {
                return right_team_cloth;
            }

            public void setRight_team_cloth(String right_team_cloth) {
                this.right_team_cloth = right_team_cloth;
            }

            public String getDetail_time() {
                return detail_time;
            }

            public void setDetail_time(String detail_time) {
                this.detail_time = detail_time;
            }

            public String getRight_team_glag() {
                return right_team_glag;
            }

            public void setRight_team_glag(String right_team_glag) {
                this.right_team_glag = right_team_glag;
            }

            public String getLeft_team_name() {
                return left_team_name;
            }

            public void setLeft_team_name(String left_team_name) {
                this.left_team_name = left_team_name;
            }

            public static class RedCardBean {
                private LeftRedCardBean left_red_card;
                private RightRedCardBean right_red_card;

                public LeftRedCardBean getLeft_red_card() {
                    return left_red_card;
                }

                public void setLeft_red_card(LeftRedCardBean left_red_card) {
                    this.left_red_card = left_red_card;
                }

                public RightRedCardBean getRight_red_card() {
                    return right_red_card;
                }

                public void setRight_red_card(RightRedCardBean right_red_card) {
                    this.right_red_card = right_red_card;
                }

                public static class LeftRedCardBean {
                    /**
                     * num : 88
                     */

                    private List<LeftRedCardNumBean> left_red_card_num;
                    /**
                     * name : 西朗读
                     */

                    private List<LeftRedCardNameBean> left_red_card_name;

                    public List<LeftRedCardNumBean> getLeft_red_card_num() {
                        return left_red_card_num;
                    }

                    public void setLeft_red_card_num(List<LeftRedCardNumBean> left_red_card_num) {
                        this.left_red_card_num = left_red_card_num;
                    }

                    public List<LeftRedCardNameBean> getLeft_red_card_name() {
                        return left_red_card_name;
                    }

                    public void setLeft_red_card_name(List<LeftRedCardNameBean> left_red_card_name) {
                        this.left_red_card_name = left_red_card_name;
                    }

                    public static class LeftRedCardNumBean {
                        private String num;

                        public String getNum() {
                            return num;
                        }

                        public void setNum(String num) {
                            this.num = num;
                        }
                    }

                    public static class LeftRedCardNameBean {
                        private String name;

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }
                    }
                }

                public static class RightRedCardBean {
                    /**
                     * num : 77
                     */

                    private List<RightRedCardNumBean> right_red_card_num;
                    /**
                     * name : 大伟
                     */

                    private List<RightRedCardNameBean> right_red_card_name;

                    public List<RightRedCardNumBean> getRight_red_card_num() {
                        return right_red_card_num;
                    }

                    public void setRight_red_card_num(List<RightRedCardNumBean> right_red_card_num) {
                        this.right_red_card_num = right_red_card_num;
                    }

                    public List<RightRedCardNameBean> getRight_red_card_name() {
                        return right_red_card_name;
                    }

                    public void setRight_red_card_name(List<RightRedCardNameBean> right_red_card_name) {
                        this.right_red_card_name = right_red_card_name;
                    }

                    public static class RightRedCardNumBean {
                        private String num;

                        public String getNum() {
                            return num;
                        }

                        public void setNum(String num) {
                            this.num = num;
                        }
                    }

                    public static class RightRedCardNameBean {
                        private String name;

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }
                    }
                }
            }

            public static class JudgeOanelBean {
                private String second_judge;
                private String primary_judge;
                private String fourth_officious;

                public String getSecond_judge() {
                    return second_judge;
                }

                public void setSecond_judge(String second_judge) {
                    this.second_judge = second_judge;
                }

                public String getPrimary_judge() {
                    return primary_judge;
                }

                public void setPrimary_judge(String primary_judge) {
                    this.primary_judge = primary_judge;
                }

                public String getFourth_officious() {
                    return fourth_officious;
                }

                public void setFourth_officious(String fourth_officious) {
                    this.fourth_officious = fourth_officious;
                }
            }

            public static class YellowCardBean {
                private LeftYellowCardBean left_yellow_card;
                private RightYellowCardBean right_yellow_card;

                public LeftYellowCardBean getLeft_yellow_card() {
                    return left_yellow_card;
                }

                public void setLeft_yellow_card(LeftYellowCardBean left_yellow_card) {
                    this.left_yellow_card = left_yellow_card;
                }

                public RightYellowCardBean getRight_yellow_card() {
                    return right_yellow_card;
                }

                public void setRight_yellow_card(RightYellowCardBean right_yellow_card) {
                    this.right_yellow_card = right_yellow_card;
                }

                public static class LeftYellowCardBean {
                    /**
                     * name : 大伟
                     */

                    private List<LeftYellowCardNameBean> left_yellow_card_name;
                    /**
                     * num : 44
                     */

                    private List<LeftYellowCardNumBean> left_yellow_card_num;

                    public List<LeftYellowCardNameBean> getLeft_yellow_card_name() {
                        return left_yellow_card_name;
                    }

                    public void setLeft_yellow_card_name(List<LeftYellowCardNameBean> left_yellow_card_name) {
                        this.left_yellow_card_name = left_yellow_card_name;
                    }

                    public List<LeftYellowCardNumBean> getLeft_yellow_card_num() {
                        return left_yellow_card_num;
                    }

                    public void setLeft_yellow_card_num(List<LeftYellowCardNumBean> left_yellow_card_num) {
                        this.left_yellow_card_num = left_yellow_card_num;
                    }

                    public static class LeftYellowCardNameBean {
                        private String name;

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }
                    }

                    public static class LeftYellowCardNumBean {
                        private String num;

                        public String getNum() {
                            return num;
                        }

                        public void setNum(String num) {
                            this.num = num;
                        }
                    }
                }

                public static class RightYellowCardBean {
                    /**
                     * num : 33
                     */

                    private List<RightYellowCardNumBean> right_yellow_card_num;
                    /**
                     * name : 西朗读
                     */

                    private List<RightYellowCardNameBean> right_yellow_card_name;

                    public List<RightYellowCardNumBean> getRight_yellow_card_num() {
                        return right_yellow_card_num;
                    }

                    public void setRight_yellow_card_num(List<RightYellowCardNumBean> right_yellow_card_num) {
                        this.right_yellow_card_num = right_yellow_card_num;
                    }

                    public List<RightYellowCardNameBean> getRight_yellow_card_name() {
                        return right_yellow_card_name;
                    }

                    public void setRight_yellow_card_name(List<RightYellowCardNameBean> right_yellow_card_name) {
                        this.right_yellow_card_name = right_yellow_card_name;
                    }

                    public static class RightYellowCardNumBean {
                        private String num;

                        public String getNum() {
                            return num;
                        }

                        public void setNum(String num) {
                            this.num = num;
                        }
                    }

                    public static class RightYellowCardNameBean {
                        private String name;

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }
                    }
                }
            }

            public static class AssitBean {
                private LeftAssitBean left_assit;
                private RightAssitBean right_assit;

                public LeftAssitBean getLeft_assit() {
                    return left_assit;
                }

                public void setLeft_assit(LeftAssitBean left_assit) {
                    this.left_assit = left_assit;
                }

                public RightAssitBean getRight_assit() {
                    return right_assit;
                }

                public void setRight_assit(RightAssitBean right_assit) {
                    this.right_assit = right_assit;
                }

                public static class LeftAssitBean {
                    /**
                     * name : 西朗读
                     */

                    private List<LeftAssitNameBean> left_assit_name;

                    public List<LeftAssitNameBean> getLeft_assit_name() {
                        return left_assit_name;
                    }

                    public void setLeft_assit_name(List<LeftAssitNameBean> left_assit_name) {
                        this.left_assit_name = left_assit_name;
                    }

                    public static class LeftAssitNameBean {
                        private String name;

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }
                    }
                }

                public static class RightAssitBean {
                    /**
                     * name : 西朗读
                     */

                    private List<RightAssitNameBean> right_assit_name;

                    public List<RightAssitNameBean> getRight_assit_name() {
                        return right_assit_name;
                    }

                    public void setRight_assit_name(List<RightAssitNameBean> right_assit_name) {
                        this.right_assit_name = right_assit_name;
                    }

                    public static class RightAssitNameBean {
                        private String name;

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }
                    }
                }
            }

            public static class GoalBean {
                private LeftGoalBean left_goal;
                private RightGoalBean right_goal;

                public LeftGoalBean getLeft_goal() {
                    return left_goal;
                }

                public void setLeft_goal(LeftGoalBean left_goal) {
                    this.left_goal = left_goal;
                }

                public RightGoalBean getRight_goal() {
                    return right_goal;
                }

                public void setRight_goal(RightGoalBean right_goal) {
                    this.right_goal = right_goal;
                }

                public static class LeftGoalBean {
                    /**
                     * score : 55
                     */

                    private List<LeftGoalScoreBean> left_goal_score;
                    /**
                     * name : 大伟
                     */

                    private List<LeftGoalNameBean> left_goal_name;

                    public List<LeftGoalScoreBean> getLeft_goal_score() {
                        return left_goal_score;
                    }

                    public void setLeft_goal_score(List<LeftGoalScoreBean> left_goal_score) {
                        this.left_goal_score = left_goal_score;
                    }

                    public List<LeftGoalNameBean> getLeft_goal_name() {
                        return left_goal_name;
                    }

                    public void setLeft_goal_name(List<LeftGoalNameBean> left_goal_name) {
                        this.left_goal_name = left_goal_name;
                    }

                    public static class LeftGoalScoreBean {
                        private String score;

                        public String getScore() {
                            return score;
                        }

                        public void setScore(String score) {
                            this.score = score;
                        }
                    }

                    public static class LeftGoalNameBean {
                        private String name;

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }
                    }
                }

                public static class RightGoalBean {
                    /**
                     * name : 西朗读
                     */

                    private List<RightGoalNameBean> right_goal_name;
                    /**
                     * score : 55
                     */

                    private List<RightGoalScoreBean> right_goal_score;

                    public List<RightGoalNameBean> getRight_goal_name() {
                        return right_goal_name;
                    }

                    public void setRight_goal_name(List<RightGoalNameBean> right_goal_name) {
                        this.right_goal_name = right_goal_name;
                    }

                    public List<RightGoalScoreBean> getRight_goal_score() {
                        return right_goal_score;
                    }

                    public void setRight_goal_score(List<RightGoalScoreBean> right_goal_score) {
                        this.right_goal_score = right_goal_score;
                    }

                    public static class RightGoalNameBean {
                        private String name;

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }
                    }

                    public static class RightGoalScoreBean {
                        private String score;

                        public String getScore() {
                            return score;
                        }

                        public void setScore(String score) {
                            this.score = score;
                        }
                    }
                }
            }

            public static class RatingbarBean {
                private String fair_judge;
                private String exact_judge;
                private String race_control;

                public String getFair_judge() {
                    return fair_judge;
                }

                public void setFair_judge(String fair_judge) {
                    this.fair_judge = fair_judge;
                }

                public String getExact_judge() {
                    return exact_judge;
                }

                public void setExact_judge(String exact_judge) {
                    this.exact_judge = exact_judge;
                }

                public String getRace_control() {
                    return race_control;
                }

                public void setRace_control(String race_control) {
                    this.race_control = race_control;
                }
            }
        }

        public static class SecondchilddataBean {
            /**
             * right_yellow_card : {"right_yellow_card_num":[{"num":"33"},{"num":"33"}],"right_yellow_card_name":[{"name":"西朗读"},{"name":"大伟"}]}
             * left_yellow_card : {"left_yellow_card_num":[{"num":"44"},{"num":"44"}],"left_yellow_card_name":[{"name":"大伟"},{"name":"大伟"}]}
             */

            private YellowCardBean yellow_card;
            /**
             * right_goal : {"right_goal_score":[{"score":"55"}],"right_goal_name":[{"name":"西朗读"}]}
             * left_goal : {"left_goal_name":[{"name":"大伟"},{"name":"大伟"}],"left_goal_score":[{"score":"55"},{"score":"55"}]}
             */

            private GoalBean goal;
            /**
             * left_assit : {"left_assit_name":[{"name":"西朗读"},{"name":"西朗读"}]}
             * right_assit : {"right_assit_name":[{"name":"西朗读"},{"name":"西朗读"}]}
             */

            private AssitBean assit;
            /**
             * fourth_officious : 斯塔克
             * second_judge : 伊尔马托夫，考绍伊
             * primary_judge : 里佐利
             */

            private JudgeOanelBean judge_oanel;
            /**
             * left_red_card : {"left_red_card_num":[{"num":"88"}],"left_red_card_name":[{"name":"西朗读"}]}
             * right_red_card : {"right_red_card_name":[{"name":"大伟"}],"right_red_card_num":[{"num":"77"}]}
             */

            private RedCardBean red_card;
            /**
             * exact_judge : 5
             * fair_judge : 4
             * race_control : 3
             */

            private RatingbarBean ratingbar;
            private String ball_park;
            private String left_team_name;
            private String right_team_name;
            private String detail_time;
            private String left_team_cloth;
            private String contrastScore;
            private String left_team_flag;
            private String right_team_cloth;
            private String right_team_glag;

            public YellowCardBean getYellow_card() {
                return yellow_card;
            }

            public void setYellow_card(YellowCardBean yellow_card) {
                this.yellow_card = yellow_card;
            }

            public GoalBean getGoal() {
                return goal;
            }

            public void setGoal(GoalBean goal) {
                this.goal = goal;
            }

            public AssitBean getAssit() {
                return assit;
            }

            public void setAssit(AssitBean assit) {
                this.assit = assit;
            }

            public JudgeOanelBean getJudge_oanel() {
                return judge_oanel;
            }

            public void setJudge_oanel(JudgeOanelBean judge_oanel) {
                this.judge_oanel = judge_oanel;
            }

            public RedCardBean getRed_card() {
                return red_card;
            }

            public void setRed_card(RedCardBean red_card) {
                this.red_card = red_card;
            }

            public RatingbarBean getRatingbar() {
                return ratingbar;
            }

            public void setRatingbar(RatingbarBean ratingbar) {
                this.ratingbar = ratingbar;
            }

            public String getBall_park() {
                return ball_park;
            }

            public void setBall_park(String ball_park) {
                this.ball_park = ball_park;
            }

            public String getLeft_team_name() {
                return left_team_name;
            }

            public void setLeft_team_name(String left_team_name) {
                this.left_team_name = left_team_name;
            }

            public String getRight_team_name() {
                return right_team_name;
            }

            public void setRight_team_name(String right_team_name) {
                this.right_team_name = right_team_name;
            }

            public String getDetail_time() {
                return detail_time;
            }

            public void setDetail_time(String detail_time) {
                this.detail_time = detail_time;
            }

            public String getLeft_team_cloth() {
                return left_team_cloth;
            }

            public void setLeft_team_cloth(String left_team_cloth) {
                this.left_team_cloth = left_team_cloth;
            }

            public String getContrastScore() {
                return contrastScore;
            }

            public void setContrastScore(String contrastScore) {
                this.contrastScore = contrastScore;
            }

            public String getLeft_team_flag() {
                return left_team_flag;
            }

            public void setLeft_team_flag(String left_team_flag) {
                this.left_team_flag = left_team_flag;
            }

            public String getRight_team_cloth() {
                return right_team_cloth;
            }

            public void setRight_team_cloth(String right_team_cloth) {
                this.right_team_cloth = right_team_cloth;
            }

            public String getRight_team_glag() {
                return right_team_glag;
            }

            public void setRight_team_glag(String right_team_glag) {
                this.right_team_glag = right_team_glag;
            }

            public static class YellowCardBean {
                private RightYellowCardBean right_yellow_card;
                private LeftYellowCardBean left_yellow_card;

                public RightYellowCardBean getRight_yellow_card() {
                    return right_yellow_card;
                }

                public void setRight_yellow_card(RightYellowCardBean right_yellow_card) {
                    this.right_yellow_card = right_yellow_card;
                }

                public LeftYellowCardBean getLeft_yellow_card() {
                    return left_yellow_card;
                }

                public void setLeft_yellow_card(LeftYellowCardBean left_yellow_card) {
                    this.left_yellow_card = left_yellow_card;
                }

                public static class RightYellowCardBean {
                    /**
                     * num : 33
                     */

                    private List<RightYellowCardNumBean> right_yellow_card_num;
                    /**
                     * name : 西朗读
                     */

                    private List<RightYellowCardNameBean> right_yellow_card_name;

                    public List<RightYellowCardNumBean> getRight_yellow_card_num() {
                        return right_yellow_card_num;
                    }

                    public void setRight_yellow_card_num(List<RightYellowCardNumBean> right_yellow_card_num) {
                        this.right_yellow_card_num = right_yellow_card_num;
                    }

                    public List<RightYellowCardNameBean> getRight_yellow_card_name() {
                        return right_yellow_card_name;
                    }

                    public void setRight_yellow_card_name(List<RightYellowCardNameBean> right_yellow_card_name) {
                        this.right_yellow_card_name = right_yellow_card_name;
                    }

                    public static class RightYellowCardNumBean {
                        private String num;

                        public String getNum() {
                            return num;
                        }

                        public void setNum(String num) {
                            this.num = num;
                        }
                    }

                    public static class RightYellowCardNameBean {
                        private String name;

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }
                    }
                }

                public static class LeftYellowCardBean {
                    /**
                     * num : 44
                     */

                    private List<LeftYellowCardNumBean> left_yellow_card_num;
                    /**
                     * name : 大伟
                     */

                    private List<LeftYellowCardNameBean> left_yellow_card_name;

                    public List<LeftYellowCardNumBean> getLeft_yellow_card_num() {
                        return left_yellow_card_num;
                    }

                    public void setLeft_yellow_card_num(List<LeftYellowCardNumBean> left_yellow_card_num) {
                        this.left_yellow_card_num = left_yellow_card_num;
                    }

                    public List<LeftYellowCardNameBean> getLeft_yellow_card_name() {
                        return left_yellow_card_name;
                    }

                    public void setLeft_yellow_card_name(List<LeftYellowCardNameBean> left_yellow_card_name) {
                        this.left_yellow_card_name = left_yellow_card_name;
                    }

                    public static class LeftYellowCardNumBean {
                        private String num;

                        public String getNum() {
                            return num;
                        }

                        public void setNum(String num) {
                            this.num = num;
                        }
                    }

                    public static class LeftYellowCardNameBean {
                        private String name;

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }
                    }
                }
            }

            public static class GoalBean {
                private RightGoalBean right_goal;
                private LeftGoalBean left_goal;

                public RightGoalBean getRight_goal() {
                    return right_goal;
                }

                public void setRight_goal(RightGoalBean right_goal) {
                    this.right_goal = right_goal;
                }

                public LeftGoalBean getLeft_goal() {
                    return left_goal;
                }

                public void setLeft_goal(LeftGoalBean left_goal) {
                    this.left_goal = left_goal;
                }

                public static class RightGoalBean {
                    /**
                     * score : 55
                     */

                    private List<RightGoalScoreBean> right_goal_score;
                    /**
                     * name : 西朗读
                     */

                    private List<RightGoalNameBean> right_goal_name;

                    public List<RightGoalScoreBean> getRight_goal_score() {
                        return right_goal_score;
                    }

                    public void setRight_goal_score(List<RightGoalScoreBean> right_goal_score) {
                        this.right_goal_score = right_goal_score;
                    }

                    public List<RightGoalNameBean> getRight_goal_name() {
                        return right_goal_name;
                    }

                    public void setRight_goal_name(List<RightGoalNameBean> right_goal_name) {
                        this.right_goal_name = right_goal_name;
                    }

                    public static class RightGoalScoreBean {
                        private String score;

                        public String getScore() {
                            return score;
                        }

                        public void setScore(String score) {
                            this.score = score;
                        }
                    }

                    public static class RightGoalNameBean {
                        private String name;

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }
                    }
                }

                public static class LeftGoalBean {
                    /**
                     * name : 大伟
                     */

                    private List<LeftGoalNameBean> left_goal_name;
                    /**
                     * score : 55
                     */

                    private List<LeftGoalScoreBean> left_goal_score;

                    public List<LeftGoalNameBean> getLeft_goal_name() {
                        return left_goal_name;
                    }

                    public void setLeft_goal_name(List<LeftGoalNameBean> left_goal_name) {
                        this.left_goal_name = left_goal_name;
                    }

                    public List<LeftGoalScoreBean> getLeft_goal_score() {
                        return left_goal_score;
                    }

                    public void setLeft_goal_score(List<LeftGoalScoreBean> left_goal_score) {
                        this.left_goal_score = left_goal_score;
                    }

                    public static class LeftGoalNameBean {
                        private String name;

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }
                    }

                    public static class LeftGoalScoreBean {
                        private String score;

                        public String getScore() {
                            return score;
                        }

                        public void setScore(String score) {
                            this.score = score;
                        }
                    }
                }
            }

            public static class AssitBean {
                private LeftAssitBean left_assit;
                private RightAssitBean right_assit;

                public LeftAssitBean getLeft_assit() {
                    return left_assit;
                }

                public void setLeft_assit(LeftAssitBean left_assit) {
                    this.left_assit = left_assit;
                }

                public RightAssitBean getRight_assit() {
                    return right_assit;
                }

                public void setRight_assit(RightAssitBean right_assit) {
                    this.right_assit = right_assit;
                }

                public static class LeftAssitBean {
                    /**
                     * name : 西朗读
                     */

                    private List<LeftAssitNameBean> left_assit_name;

                    public List<LeftAssitNameBean> getLeft_assit_name() {
                        return left_assit_name;
                    }

                    public void setLeft_assit_name(List<LeftAssitNameBean> left_assit_name) {
                        this.left_assit_name = left_assit_name;
                    }

                    public static class LeftAssitNameBean {
                        private String name;

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }
                    }
                }

                public static class RightAssitBean {
                    /**
                     * name : 西朗读
                     */

                    private List<RightAssitNameBean> right_assit_name;

                    public List<RightAssitNameBean> getRight_assit_name() {
                        return right_assit_name;
                    }

                    public void setRight_assit_name(List<RightAssitNameBean> right_assit_name) {
                        this.right_assit_name = right_assit_name;
                    }

                    public static class RightAssitNameBean {
                        private String name;

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }
                    }
                }
            }

            public static class JudgeOanelBean {
                private String fourth_officious;
                private String second_judge;
                private String primary_judge;

                public String getFourth_officious() {
                    return fourth_officious;
                }

                public void setFourth_officious(String fourth_officious) {
                    this.fourth_officious = fourth_officious;
                }

                public String getSecond_judge() {
                    return second_judge;
                }

                public void setSecond_judge(String second_judge) {
                    this.second_judge = second_judge;
                }

                public String getPrimary_judge() {
                    return primary_judge;
                }

                public void setPrimary_judge(String primary_judge) {
                    this.primary_judge = primary_judge;
                }
            }

            public static class RedCardBean {
                private LeftRedCardBean left_red_card;
                private RightRedCardBean right_red_card;

                public LeftRedCardBean getLeft_red_card() {
                    return left_red_card;
                }

                public void setLeft_red_card(LeftRedCardBean left_red_card) {
                    this.left_red_card = left_red_card;
                }

                public RightRedCardBean getRight_red_card() {
                    return right_red_card;
                }

                public void setRight_red_card(RightRedCardBean right_red_card) {
                    this.right_red_card = right_red_card;
                }

                public static class LeftRedCardBean {
                    /**
                     * num : 88
                     */

                    private List<LeftRedCardNumBean> left_red_card_num;
                    /**
                     * name : 西朗读
                     */

                    private List<LeftRedCardNameBean> left_red_card_name;

                    public List<LeftRedCardNumBean> getLeft_red_card_num() {
                        return left_red_card_num;
                    }

                    public void setLeft_red_card_num(List<LeftRedCardNumBean> left_red_card_num) {
                        this.left_red_card_num = left_red_card_num;
                    }

                    public List<LeftRedCardNameBean> getLeft_red_card_name() {
                        return left_red_card_name;
                    }

                    public void setLeft_red_card_name(List<LeftRedCardNameBean> left_red_card_name) {
                        this.left_red_card_name = left_red_card_name;
                    }

                    public static class LeftRedCardNumBean {
                        private String num;

                        public String getNum() {
                            return num;
                        }

                        public void setNum(String num) {
                            this.num = num;
                        }
                    }

                    public static class LeftRedCardNameBean {
                        private String name;

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }
                    }
                }

                public static class RightRedCardBean {
                    /**
                     * name : 大伟
                     */

                    private List<RightRedCardNameBean> right_red_card_name;
                    /**
                     * num : 77
                     */

                    private List<RightRedCardNumBean> right_red_card_num;

                    public List<RightRedCardNameBean> getRight_red_card_name() {
                        return right_red_card_name;
                    }

                    public void setRight_red_card_name(List<RightRedCardNameBean> right_red_card_name) {
                        this.right_red_card_name = right_red_card_name;
                    }

                    public List<RightRedCardNumBean> getRight_red_card_num() {
                        return right_red_card_num;
                    }

                    public void setRight_red_card_num(List<RightRedCardNumBean> right_red_card_num) {
                        this.right_red_card_num = right_red_card_num;
                    }

                    public static class RightRedCardNameBean {
                        private String name;

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }
                    }

                    public static class RightRedCardNumBean {
                        private String num;

                        public String getNum() {
                            return num;
                        }

                        public void setNum(String num) {
                            this.num = num;
                        }
                    }
                }
            }

            public static class RatingbarBean {
                private String exact_judge;
                private String fair_judge;
                private String race_control;

                public String getExact_judge() {
                    return exact_judge;
                }

                public void setExact_judge(String exact_judge) {
                    this.exact_judge = exact_judge;
                }

                public String getFair_judge() {
                    return fair_judge;
                }

                public void setFair_judge(String fair_judge) {
                    this.fair_judge = fair_judge;
                }

                public String getRace_control() {
                    return race_control;
                }

                public void setRace_control(String race_control) {
                    this.race_control = race_control;
                }
            }
        }

        public static class FirstchilddataBean {
            /**
             * left_goal : {"left_goal_score":[{"score":"55"},{"score":"55"}],"left_goal_name":[{"name":"大伟"},{"name":"大伟"}]}
             * right_goal : {"right_goal_name":[{"name":"西朗读"}],"right_goal_score":[{"score":"55"}]}
             */

            private GoalBean goal;
            /**
             * left_assit : {"left_assit_name":[{"name":"西朗读"},{"name":"西朗读"}]}
             * right_assit : {"right_assit_name":[{"name":"西朗读"},{"name":"西朗读"}]}
             */

            private AssitBean assit;
            /**
             * fair_judge : 4
             * exact_judge : 5
             * race_control : 3
             */

            private RatingbarBean ratingbar;
            /**
             * right_yellow_card : {"right_yellow_card_name":[{"name":"西朗读"},{"name":"大伟"}],"right_yellow_card_num":[{"num":"33"},{"num":"33"}]}
             * left_yellow_card : {"left_yellow_card_num":[{"num":"44"},{"num":"44"}],"left_yellow_card_name":[{"name":"大伟"},{"name":"大伟"}]}
             */

            private YellowCardBean yellow_card;
            /**
             * left_red_card : {"left_red_card_name":[{"name":"西朗读"}],"left_red_card_num":[{"num":"88"}]}
             * right_red_card : {"right_red_card_name":[{"name":"大伟"}],"right_red_card_num":[{"num":"77"}]}
             */

            private RedCardBean red_card;
            /**
             * primary_judge : 里佐利
             * fourth_officious : 斯塔克
             * second_judge : 伊尔马托夫，考绍伊
             */

            private JudgeOanelBean judge_oanel;
            private String detail_time;
            private String left_team_flag;
            private String right_team_cloth;
            private String left_team_cloth;
            private String right_team_name;
            private String ball_park;
            private String right_team_glag;
            private String contrastScore;
            private String left_team_name;

            public GoalBean getGoal() {
                return goal;
            }

            public void setGoal(GoalBean goal) {
                this.goal = goal;
            }

            public AssitBean getAssit() {
                return assit;
            }

            public void setAssit(AssitBean assit) {
                this.assit = assit;
            }

            public RatingbarBean getRatingbar() {
                return ratingbar;
            }

            public void setRatingbar(RatingbarBean ratingbar) {
                this.ratingbar = ratingbar;
            }

            public YellowCardBean getYellow_card() {
                return yellow_card;
            }

            public void setYellow_card(YellowCardBean yellow_card) {
                this.yellow_card = yellow_card;
            }

            public RedCardBean getRed_card() {
                return red_card;
            }

            public void setRed_card(RedCardBean red_card) {
                this.red_card = red_card;
            }

            public JudgeOanelBean getJudge_oanel() {
                return judge_oanel;
            }

            public void setJudge_oanel(JudgeOanelBean judge_oanel) {
                this.judge_oanel = judge_oanel;
            }

            public String getDetail_time() {
                return detail_time;
            }

            public void setDetail_time(String detail_time) {
                this.detail_time = detail_time;
            }

            public String getLeft_team_flag() {
                return left_team_flag;
            }

            public void setLeft_team_flag(String left_team_flag) {
                this.left_team_flag = left_team_flag;
            }

            public String getRight_team_cloth() {
                return right_team_cloth;
            }

            public void setRight_team_cloth(String right_team_cloth) {
                this.right_team_cloth = right_team_cloth;
            }

            public String getLeft_team_cloth() {
                return left_team_cloth;
            }

            public void setLeft_team_cloth(String left_team_cloth) {
                this.left_team_cloth = left_team_cloth;
            }

            public String getRight_team_name() {
                return right_team_name;
            }

            public void setRight_team_name(String right_team_name) {
                this.right_team_name = right_team_name;
            }

            public String getBall_park() {
                return ball_park;
            }

            public void setBall_park(String ball_park) {
                this.ball_park = ball_park;
            }

            public String getRight_team_glag() {
                return right_team_glag;
            }

            public void setRight_team_glag(String right_team_glag) {
                this.right_team_glag = right_team_glag;
            }

            public String getContrastScore() {
                return contrastScore;
            }

            public void setContrastScore(String contrastScore) {
                this.contrastScore = contrastScore;
            }

            public String getLeft_team_name() {
                return left_team_name;
            }

            public void setLeft_team_name(String left_team_name) {
                this.left_team_name = left_team_name;
            }

            public static class GoalBean {
                private LeftGoalBean left_goal;
                private RightGoalBean right_goal;

                public LeftGoalBean getLeft_goal() {
                    return left_goal;
                }

                public void setLeft_goal(LeftGoalBean left_goal) {
                    this.left_goal = left_goal;
                }

                public RightGoalBean getRight_goal() {
                    return right_goal;
                }

                public void setRight_goal(RightGoalBean right_goal) {
                    this.right_goal = right_goal;
                }

                public static class LeftGoalBean {
                    /**
                     * score : 55
                     */

                    private List<LeftGoalScoreBean> left_goal_score;
                    /**
                     * name : 大伟
                     */

                    private List<LeftGoalNameBean> left_goal_name;

                    public List<LeftGoalScoreBean> getLeft_goal_score() {
                        return left_goal_score;
                    }

                    public void setLeft_goal_score(List<LeftGoalScoreBean> left_goal_score) {
                        this.left_goal_score = left_goal_score;
                    }

                    public List<LeftGoalNameBean> getLeft_goal_name() {
                        return left_goal_name;
                    }

                    public void setLeft_goal_name(List<LeftGoalNameBean> left_goal_name) {
                        this.left_goal_name = left_goal_name;
                    }

                    public static class LeftGoalScoreBean {
                        private String score;

                        public String getScore() {
                            return score;
                        }

                        public void setScore(String score) {
                            this.score = score;
                        }
                    }

                    public static class LeftGoalNameBean {
                        private String name;

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }
                    }
                }

                public static class RightGoalBean {
                    /**
                     * name : 西朗读
                     */

                    private List<RightGoalNameBean> right_goal_name;
                    /**
                     * score : 55
                     */

                    private List<RightGoalScoreBean> right_goal_score;

                    public List<RightGoalNameBean> getRight_goal_name() {
                        return right_goal_name;
                    }

                    public void setRight_goal_name(List<RightGoalNameBean> right_goal_name) {
                        this.right_goal_name = right_goal_name;
                    }

                    public List<RightGoalScoreBean> getRight_goal_score() {
                        return right_goal_score;
                    }

                    public void setRight_goal_score(List<RightGoalScoreBean> right_goal_score) {
                        this.right_goal_score = right_goal_score;
                    }

                    public static class RightGoalNameBean {
                        private String name;

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }
                    }

                    public static class RightGoalScoreBean {
                        private String score;

                        public String getScore() {
                            return score;
                        }

                        public void setScore(String score) {
                            this.score = score;
                        }
                    }
                }
            }

            public static class AssitBean {
                private LeftAssitBean left_assit;
                private RightAssitBean right_assit;

                public LeftAssitBean getLeft_assit() {
                    return left_assit;
                }

                public void setLeft_assit(LeftAssitBean left_assit) {
                    this.left_assit = left_assit;
                }

                public RightAssitBean getRight_assit() {
                    return right_assit;
                }

                public void setRight_assit(RightAssitBean right_assit) {
                    this.right_assit = right_assit;
                }

                public static class LeftAssitBean {
                    /**
                     * name : 西朗读
                     */

                    private List<LeftAssitNameBean> left_assit_name;

                    public List<LeftAssitNameBean> getLeft_assit_name() {
                        return left_assit_name;
                    }

                    public void setLeft_assit_name(List<LeftAssitNameBean> left_assit_name) {
                        this.left_assit_name = left_assit_name;
                    }

                    public static class LeftAssitNameBean {
                        private String name;

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }
                    }
                }

                public static class RightAssitBean {
                    /**
                     * name : 西朗读
                     */

                    private List<RightAssitNameBean> right_assit_name;

                    public List<RightAssitNameBean> getRight_assit_name() {
                        return right_assit_name;
                    }

                    public void setRight_assit_name(List<RightAssitNameBean> right_assit_name) {
                        this.right_assit_name = right_assit_name;
                    }

                    public static class RightAssitNameBean {
                        private String name;

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }
                    }
                }
            }

            public static class RatingbarBean {
                private String fair_judge;
                private String exact_judge;
                private String race_control;

                public String getFair_judge() {
                    return fair_judge;
                }

                public void setFair_judge(String fair_judge) {
                    this.fair_judge = fair_judge;
                }

                public String getExact_judge() {
                    return exact_judge;
                }

                public void setExact_judge(String exact_judge) {
                    this.exact_judge = exact_judge;
                }

                public String getRace_control() {
                    return race_control;
                }

                public void setRace_control(String race_control) {
                    this.race_control = race_control;
                }
            }

            public static class YellowCardBean {
                private RightYellowCardBean right_yellow_card;
                private LeftYellowCardBean left_yellow_card;

                public RightYellowCardBean getRight_yellow_card() {
                    return right_yellow_card;
                }

                public void setRight_yellow_card(RightYellowCardBean right_yellow_card) {
                    this.right_yellow_card = right_yellow_card;
                }

                public LeftYellowCardBean getLeft_yellow_card() {
                    return left_yellow_card;
                }

                public void setLeft_yellow_card(LeftYellowCardBean left_yellow_card) {
                    this.left_yellow_card = left_yellow_card;
                }

                public static class RightYellowCardBean {
                    /**
                     * name : 西朗读
                     */

                    private List<RightYellowCardNameBean> right_yellow_card_name;
                    /**
                     * num : 33
                     */

                    private List<RightYellowCardNumBean> right_yellow_card_num;

                    public List<RightYellowCardNameBean> getRight_yellow_card_name() {
                        return right_yellow_card_name;
                    }

                    public void setRight_yellow_card_name(List<RightYellowCardNameBean> right_yellow_card_name) {
                        this.right_yellow_card_name = right_yellow_card_name;
                    }

                    public List<RightYellowCardNumBean> getRight_yellow_card_num() {
                        return right_yellow_card_num;
                    }

                    public void setRight_yellow_card_num(List<RightYellowCardNumBean> right_yellow_card_num) {
                        this.right_yellow_card_num = right_yellow_card_num;
                    }

                    public static class RightYellowCardNameBean {
                        private String name;

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }
                    }

                    public static class RightYellowCardNumBean {
                        private String num;

                        public String getNum() {
                            return num;
                        }

                        public void setNum(String num) {
                            this.num = num;
                        }
                    }
                }

                public static class LeftYellowCardBean {
                    /**
                     * num : 44
                     */

                    private List<LeftYellowCardNumBean> left_yellow_card_num;
                    /**
                     * name : 大伟
                     */

                    private List<LeftYellowCardNameBean> left_yellow_card_name;

                    public List<LeftYellowCardNumBean> getLeft_yellow_card_num() {
                        return left_yellow_card_num;
                    }

                    public void setLeft_yellow_card_num(List<LeftYellowCardNumBean> left_yellow_card_num) {
                        this.left_yellow_card_num = left_yellow_card_num;
                    }

                    public List<LeftYellowCardNameBean> getLeft_yellow_card_name() {
                        return left_yellow_card_name;
                    }

                    public void setLeft_yellow_card_name(List<LeftYellowCardNameBean> left_yellow_card_name) {
                        this.left_yellow_card_name = left_yellow_card_name;
                    }

                    public static class LeftYellowCardNumBean {
                        private String num;

                        public String getNum() {
                            return num;
                        }

                        public void setNum(String num) {
                            this.num = num;
                        }
                    }

                    public static class LeftYellowCardNameBean {
                        private String name;

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }
                    }
                }
            }

            public static class RedCardBean {
                private LeftRedCardBean left_red_card;
                private RightRedCardBean right_red_card;

                public LeftRedCardBean getLeft_red_card() {
                    return left_red_card;
                }

                public void setLeft_red_card(LeftRedCardBean left_red_card) {
                    this.left_red_card = left_red_card;
                }

                public RightRedCardBean getRight_red_card() {
                    return right_red_card;
                }

                public void setRight_red_card(RightRedCardBean right_red_card) {
                    this.right_red_card = right_red_card;
                }

                public static class LeftRedCardBean {
                    /**
                     * name : 西朗读
                     */

                    private List<LeftRedCardNameBean> left_red_card_name;
                    /**
                     * num : 88
                     */

                    private List<LeftRedCardNumBean> left_red_card_num;

                    public List<LeftRedCardNameBean> getLeft_red_card_name() {
                        return left_red_card_name;
                    }

                    public void setLeft_red_card_name(List<LeftRedCardNameBean> left_red_card_name) {
                        this.left_red_card_name = left_red_card_name;
                    }

                    public List<LeftRedCardNumBean> getLeft_red_card_num() {
                        return left_red_card_num;
                    }

                    public void setLeft_red_card_num(List<LeftRedCardNumBean> left_red_card_num) {
                        this.left_red_card_num = left_red_card_num;
                    }

                    public static class LeftRedCardNameBean {
                        private String name;

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }
                    }

                    public static class LeftRedCardNumBean {
                        private String num;

                        public String getNum() {
                            return num;
                        }

                        public void setNum(String num) {
                            this.num = num;
                        }
                    }
                }

                public static class RightRedCardBean {
                    /**
                     * name : 大伟
                     */

                    private List<RightRedCardNameBean> right_red_card_name;
                    /**
                     * num : 77
                     */

                    private List<RightRedCardNumBean> right_red_card_num;

                    public List<RightRedCardNameBean> getRight_red_card_name() {
                        return right_red_card_name;
                    }

                    public void setRight_red_card_name(List<RightRedCardNameBean> right_red_card_name) {
                        this.right_red_card_name = right_red_card_name;
                    }

                    public List<RightRedCardNumBean> getRight_red_card_num() {
                        return right_red_card_num;
                    }

                    public void setRight_red_card_num(List<RightRedCardNumBean> right_red_card_num) {
                        this.right_red_card_num = right_red_card_num;
                    }

                    public static class RightRedCardNameBean {
                        private String name;

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }
                    }

                    public static class RightRedCardNumBean {
                        private String num;

                        public String getNum() {
                            return num;
                        }

                        public void setNum(String num) {
                            this.num = num;
                        }
                    }
                }
            }

            public static class JudgeOanelBean {
                private String primary_judge;
                private String fourth_officious;
                private String second_judge;

                public String getPrimary_judge() {
                    return primary_judge;
                }

                public void setPrimary_judge(String primary_judge) {
                    this.primary_judge = primary_judge;
                }

                public String getFourth_officious() {
                    return fourth_officious;
                }

                public void setFourth_officious(String fourth_officious) {
                    this.fourth_officious = fourth_officious;
                }

                public String getSecond_judge() {
                    return second_judge;
                }

                public void setSecond_judge(String second_judge) {
                    this.second_judge = second_judge;
                }
            }
        }
    }
}

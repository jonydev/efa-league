package com.apsoft.scfb.http;


import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.Request;

import org.jsoup.Connection;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2016/8/9.
 */
public class NetHomeQuery {
//    public static String host_name = "http://192.168.16.70:8000";
    public static String host_name = NetSetting.s_scfbeddress;
    public static String area_info_url = host_name + "/home/area_info/";
    public static String main_info_url = host_name + "/home/main_info/";
    public static String home_new_url=host_name+"/home/news_list/";
    public static String team_list_url=host_name+"/team/list/";
    public static String team_detai_url=host_name+"/team/info/";
    public static String team_create_url=host_name+"/team/create/";
    public static String team_update_url=host_name+"/team/update/";
    public static String team_user_acquire_join_url=host_name+"/team/team_acquire/";
    public static String team_list_user_acquire_url=host_name+"/team/team_list_acquires/";
    public static String team_agree_user_acquire_url=host_name+"/team/team_agree_acquire/";
    public static String team_disagree_user_acquire_url=host_name+"/team/team_refuse_acquire/";
    public static String team_score_detail_url=host_name+"/team/score_detail/";
    public static String team_schedule_list_url=host_name+"/team/team_schedules/";
    public static String match_info_url = host_name+"/match/introduction/";
    public static String match_game_schedule_url = host_name+"/match/match_schedule/";
    public static String match_schedule_detail_url = host_name+"/match/schedule_detail/";
    public static String match_rank_score_list_url = host_name+"/match/rank_score_list/";
    public static String match_team_join_url = host_name+"/match/team_join_match/";
    public static String match_user_join_match_url = host_name+"/match/user_acquire_join/";
    public static String match_get_team_join_members_url = host_name+"/match/get_team_join_users/";
    public static String match_schedule_join_members_url = host_name+"/match/get_match_schedule_join_members/";
    public static String match_user_stop_join_url = host_name+"/match/user_acquire_stop_join/";
    public static String match_evaluate_referee_url = host_name+"/match/evaluate_referee/";
    public static String match_discuss_list_url = host_name+"/match/discuss_list/";
    public static String match_discuss_detail_url = host_name+"/match/discuss_detail/";
    public static String match_send_discuss_url = host_name+"/match/send_discuss/";
    /**
     * 区域列表
     * @param callback
     */
    public static void requestArereInfo(BaseCallback callback){
        OkHttpHelper okHttpHelper = OkHttpHelper.getInstance();
//        final Request request = new Request.Builder()
//                .url(area_info_url)
//                .build();
//        okHttpHelper.request(request, callback);
        okHttpHelper.get(area_info_url, callback);
    }

    /**
     *首页
     * @param areaId
     * @param callback
     */
    public static void requestHomeInfo(String areaId, BaseCallback callback){
        OkHttpHelper okHttpHelper = OkHttpHelper.getInstance();
//        FormEncodingBuilder formEncodingBuilder = new FormEncodingBuilder();
//        formEncodingBuilder.add("area_id", areaId);
//        final Request request = new Request.Builder()
//                .url(main_info_url).post(formEncodingBuilder.build())
//                .build();
//        okHttpHelper.request(request, callback);
        Map<String, String> params = new HashMap<>();
        params.put("area_id", areaId);
        okHttpHelper.post(main_info_url, params, callback);
    }


    /**
     * 联赛新闻
     * @param areaId
     * @param pageIndex
     * @param pageSize
     * @param startTime
     * @param callback
     */
    public static void requestNewList(String areaId,String  pageIndex,String pageSize,String startTime,BaseCallback callback ){
        OkHttpHelper okHttpHelper = OkHttpHelper.getInstance();
        Map<String, String> params = new HashMap<>();
        params.put("area_id",areaId);
        if(pageIndex != null)
            params.put("page_index",pageIndex);
        if(pageSize != null)
            params.put("page_size",pageSize);
        if(startTime != null)
            params.put("start_time",startTime);
        okHttpHelper.post(home_new_url,params,callback);
    }

    /**
     * 查询联赛基本信息
     * @param office_id
     * @param callback
     */
    public static void requestMatchInfo(String office_id, BaseCallback callback){
        OkHttpHelper okHttpHelper = OkHttpHelper.getInstance();
        Map<String, String> params = new HashMap<>();
        if(office_id!=null)
            params.put("office_id",office_id);
        okHttpHelper.post(match_info_url,params,callback);
    }


    /**
     * 查询联赛赛程
     * @param office_id
     * @param callback
     */
    public static void requestMatchGameSchedule(String office_id, BaseCallback callback){
        OkHttpHelper okHttpHelper = OkHttpHelper.getInstance();
        Map<String, String> params = new HashMap<>();
        params.put("office_id",office_id);
        okHttpHelper.post(match_game_schedule_url,params,callback);
    }


    public static void requestMatchScoreList(String office_id, BaseCallback callback){
        OkHttpHelper okHttpHelper = OkHttpHelper.getInstance();
        Map<String, String> params = new HashMap<>();
        params.put("office_id",office_id);
        okHttpHelper.post(match_rank_score_list_url,params,callback);
    }


    public static void requestScheduleDetail(String scheduleId, BaseCallback callback){
        OkHttpHelper okHttpHelper = OkHttpHelper.getInstance();
        Map<String, String> params = new HashMap<>();
        params.put("schedule_id",scheduleId);
        okHttpHelper.post(match_schedule_detail_url,params,callback);
    }

    /**
     *
     * @param callback
     */
    public static void requestTeamJoinMatch(String officeId, BaseCallback callback){
        OkHttpHelper okHttpHelper = OkHttpHelper.getInstance();
        Map<String, String> params = new HashMap<>();
        params.put("office_id",officeId);
        okHttpHelper.post(match_team_join_url, params, callback);
    }

    /**
     * 用户报名参加比赛
     * @param callback
     */
    public static void requestUseJoinTeamMatch(String schedule_id, BaseCallback callback){
        OkHttpHelper okHttpHelper = OkHttpHelper.getInstance();
        Map<String, String> params = new HashMap<>();
        params.put("schedule_id",schedule_id);
        okHttpHelper.post(match_user_join_match_url,params,callback);
    }


    /**
     * 获取球队中报名的成员数量
     * @param callback
     */
    public static void requestGetTeamJoinMembers(String schedule_id, BaseCallback callback){
        OkHttpHelper okHttpHelper = OkHttpHelper.getInstance();
        Map<String, String> params = new HashMap<>();
        params.put("schedule_id",schedule_id);
        okHttpHelper.post(match_get_team_join_members_url,params,callback);
    }


    /**
     * 获取赛程对应的参赛人员，返回为列表
     * @param schedule_id
     * @param callback
     */
    public static void requestScheduleJoinMembers(String schedule_id, BaseCallback callback){
        OkHttpHelper okHttpHelper = OkHttpHelper.getInstance();
        Map<String, String> params = new HashMap<>();
        params.put("schedule_id",schedule_id);
        okHttpHelper.post(match_schedule_join_members_url,params,callback);
    }

    /**
     * 停止报名
     * @param callback
     */
    public static void requestStopJoinMatch(String schedule_id, BaseCallback callback){
        OkHttpHelper okHttpHelper = OkHttpHelper.getInstance();
        Map<String, String> params = new HashMap<>();
        params.put("schedule_id",schedule_id);
        okHttpHelper.post(match_user_stop_join_url,params,callback);
    }

    /**
     * 球队列表
     * @param pageIndex
     * @param pageSize
     * @param teamName
     * @param callback
     */
    public static void requestTeamList(String officeId, String pageIndex,String pageSize,String teamName,BaseCallback callback){
        OkHttpHelper okHttpHelper=OkHttpHelper.getInstance();
        Map<String ,String > params=new HashMap<>();
        if(officeId!=null){
            params.put("office_id", officeId);
        }
        if(pageIndex != null)
            params.put("page_index",pageIndex);
        if(pageSize!=null)
            params.put("page_size",pageSize);
        if(teamName != null)
            params.put("team_name",teamName);
        okHttpHelper.post(team_list_url,params,callback);
    }

    /**
     * 球队详情
     * @param teamId
     */
    public static void requestTeamDetail(String teamId,BaseCallback callback){
        OkHttpHelper okHttpHelper=OkHttpHelper.getInstance();
        Map<String ,String >params=new HashMap<>();
        params.put("team_id",teamId);
        okHttpHelper.post(team_detai_url,params,callback);
    }

    /**
     * 球队战绩
     * @param teamId
     * @param startTime
     * @param endTime
     * @param status
     */
    public static void requestTeamScoreDetail(String teamId,String startTime,String endTime,String status, BaseCallback callback){
        OkHttpHelper okHttpHelper=OkHttpHelper.getInstance();
        Map<String ,String > param=new HashMap<>();
        param.put("team_id",teamId);
        param.put("start_time",startTime);
        param.put("end_time",endTime);
        param.put("status",status);
        okHttpHelper.post(team_score_detail_url,param,callback);
    }

    public static void requestTeamScheduleList(String teamId, BaseCallback callback){
        OkHttpHelper okHttpHelper=OkHttpHelper.getInstance();
        Map<String ,String > param=new HashMap<>();
        param.put("team_id",teamId);
        okHttpHelper.post(team_schedule_list_url,param,callback);
    }

    /**
     * 创建球队
     * @param teamName
     * @param callback
     */
    public static void requestCreateTeam(String teamName, String teamHome, String teamContent, String logo, String upperColor, String lowerColor, BaseCallback callback){
        OkHttpHelper okHttpHelper=OkHttpHelper.getInstance();
        Map<String ,String > param=new HashMap<>();
        param.put("team_name",teamName);
        if(teamHome != null){
            param.put("team_home",teamHome);
        }
        if(teamContent!=null){
            param.put("team_content",teamContent);
        }
        if(logo != null){
            param.put("team_logo",logo);
        }
        if(upperColor!=null){
            param.put("upper_color", upperColor);
        }
        if(lowerColor!=null){
            param.put("lower_color", lowerColor);
        }
        okHttpHelper.post(team_create_url,param,callback);
    }


    public static void requestUpdateTeam(String teamId, String teamName, String teamHome, String teamContent, String logo, String upperColor, String lowerColor, BaseCallback callback){
        OkHttpHelper okHttpHelper=OkHttpHelper.getInstance();
        Map<String ,String > param=new HashMap<>();
        param.put("team_id",teamId);
        if(teamName!=null)
            param.put("team_name",teamName);
        if(teamHome != null){
            param.put("team_home",teamHome);
        }
        if(teamContent!=null){
            param.put("team_content",teamContent);
        }
        if(logo != null){
            param.put("team_logo",logo);
        }
        if(upperColor!=null){
            param.put("upper_color", upperColor);
        }
        if(lowerColor!=null){
            param.put("lower_color", lowerColor);
        }
        okHttpHelper.post(team_update_url,param,callback);
    }



    /**
     * 申请加入球队
     * @param teamId
     * @param callback
     */
    public static void requestJoinTeam(String teamId, BaseCallback callback){
        OkHttpHelper okHttpHelper=OkHttpHelper.getInstance();
        Map<String ,String > param=new HashMap<>();
        param.put("team_id",teamId);
        okHttpHelper.post(team_user_acquire_join_url,param,callback);
    }

    /**
     * 同意加入申请
     * @param uid
     * @param callback
     */
    public static void requestAcceptUserJoinTeam(String uid, BaseCallback callback){
        OkHttpHelper okHttpHelper=OkHttpHelper.getInstance();
        Map<String ,String > param=new HashMap<>();
        param.put("member_id",uid);
        okHttpHelper.post(team_agree_user_acquire_url,param,callback);
    }

    /**
     * 拒接用户加入球队
     * @param uid
     * @param callback
     */
    public static void requestRejectUserJoinTeam(String uid, BaseCallback callback){
        OkHttpHelper okHttpHelper=OkHttpHelper.getInstance();
        Map<String ,String > param=new HashMap<>();
        param.put("member_id",uid);
        okHttpHelper.post(team_disagree_user_acquire_url,param,callback);
    }


    /**
     * 列出申请加入球队的人员
     * @param callback
     */
    public static void requestListTeamAcquireUser(BaseCallback callback){
        OkHttpHelper okHttpHelper=OkHttpHelper.getInstance();
        okHttpHelper.get(team_list_user_acquire_url,callback);
    }

    public static void requestEvaluateReferee(String officeId, String scheduleId, Integer rule, Integer fair, Integer control, String content, BaseCallback callback){
        OkHttpHelper okHttpHelper=OkHttpHelper.getInstance();
        Map<String ,String > param=new HashMap<>();
        param.put("office_id",officeId);
        param.put("schedule_id",scheduleId);
        param.put("rule",String.valueOf(rule));
        param.put("fair",String.valueOf(fair));
        param.put("control",String.valueOf(control));
        param.put("content",content);
        okHttpHelper.post(match_evaluate_referee_url,param,callback);
    }

    /**
     * 讨论区列表
     * @param officeId
     * @param callback
     */
    public static void requestDiscussList(String officeId, BaseCallback callback){
        OkHttpHelper okHttpHelper=OkHttpHelper.getInstance();
        Map<String ,String > param=new HashMap<>();
        param.put("office_id",officeId);
        okHttpHelper.post(match_discuss_list_url,param,callback);
    }

    /**
     * 查看详情
     * @param discussId
     * @param callback
     */
    public static void requestDiscussDetail(String discussId, BaseCallback callback){
        OkHttpHelper okHttpHelper=OkHttpHelper.getInstance();
        Map<String ,String > param=new HashMap<>();
        param.put("discuss_id",discussId);
        okHttpHelper.post(match_discuss_detail_url,param,callback);
    }

    /**
     * 发送讨论
     * @param discussId
     * @param reviewContent
     * @param callback
     */
    public static void requestSendDiscussReview(String discussId, String reviewContent, BaseCallback callback){
        OkHttpHelper okHttpHelper=OkHttpHelper.getInstance();
        Map<String ,String > param=new HashMap<>();
        param.put("discuss_id",discussId);
        param.put("content",reviewContent);
        okHttpHelper.post(match_send_discuss_url,param,callback);
    }
}

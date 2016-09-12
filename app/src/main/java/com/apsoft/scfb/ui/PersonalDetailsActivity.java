package com.apsoft.scfb.ui;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.anywhere.utils.ToastUtils;
import com.anywhere.view.ActionSheet;
import com.apsoft.scfb.R;
import com.apsoft.scfb.app.AppGlobalTool;
import com.apsoft.scfb.bean.PersonalInfoEntry;
import com.apsoft.scfb.http.BaseCallback;
import com.apsoft.scfb.http.NetSCFBLogin;
import com.apsoft.scfb.http.NetSetting;
import com.apsoft.scfb.localdata.User;
import com.apsoft.scfb.ui.fragments.MineFragment;
import com.apsoft.scfb.utils.IconSelectHelper;
import com.apsoft.scfb.utils.ImageLoader1;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.zaaach.citypicker.CityPickerActivity;

import org.json.JSONException;
import org.json.JSONObject;
import org.simple.eventbus.EventBus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonalDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_ID_card)
    TextView tvIDCard;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.tv_telephone)
    TextView tvTelephone;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.tv_height)
    TextView tvHeight;
    @BindView(R.id.tv_weight)
    TextView tvWeight;
    @BindView(R.id.tv_age)
    TextView tvAge;
    @BindView(R.id.tv_position)
    TextView tvPosition;
    @BindView(R.id.btn_modify_user_info)
    Button btnModifyUserInfo;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_ID_card)
    EditText etIDCard;
    @BindView(R.id.et_location)
    TextView etLocation;
    @BindView(R.id.et_telephone)
    EditText etTelephone;
    @BindView(R.id.et_policyno)
    EditText etPolicyno;
    @BindView(R.id.et_sex)
    TextView etSex;
    @BindView(R.id.et_height)
    EditText etHeight;
    @BindView(R.id.et_weight)
    EditText etWeight;
    @BindView(R.id.et_age)
    EditText etAge;
    @BindView(R.id.ck_menjiang)
    CheckBox ckMenjiang;
    @BindView(R.id.ck_houwei)
    CheckBox ckHouwei;
    @BindView(R.id.ck_zhongchang)
    CheckBox ckZhongchang;
    @BindView(R.id.ck_qianfeng)
    CheckBox ckQianfeng;
    private String position;

    private ImageView ivIcon;

    String image_photo;

    boolean progressShow = false;
    ProgressDialog progressDialog;

    IconSelectHelper iconSelectHelper;
    private Integer age;
    private MainActivity s_mainActivity;
    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);

        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        ivIcon = (ImageView) findViewById(R.id.iv_icon);
        position=null;
        setCheckBoxChange();
        initPersonalData();
        btnModifyUserInfo.setOnClickListener(this);

        /**
         * 初始化图片  选择图片的路径
         */
        iconSelectHelper = new IconSelectHelper();
//        View v = ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
        LinearLayout v= (LinearLayout) findViewById(R.id.ll_personal_top);
        iconSelectHelper.initGrid(this, v, R.id.iv_icon, false);

        etLocation.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openCitySelect();
            }
        });
        flag=getIntent().getBooleanExtra("flag",false);
        etSex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActionSheet.showSelectSexSheet(PersonalDetailsActivity.this, new ActionSheet.OnActionSheetSelected() {
                    @Override
                    public void onClick(int i) {
                        switch(i){
                            case 0:
                                etSex.setText("女");
                                break;
                            case 1:
                                etSex.setText("男");
                                break;
                        }

                    }
                }, new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {

                    }
                });
            }
        });
//        ImageLoader1.getInstance().displayCricleImage(this, User.getInstance().getUser_photo(), ivIcon);
    }

    private void setCheckBoxChange() {
        ckMenjiang.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    ckZhongchang.setChecked(false);
                    ckQianfeng.setChecked(false);
                    ckHouwei.setChecked(false);
                    settextAllcolor();
                    ckMenjiang.setTextColor(Color.parseColor("#ffffff"));
                    position = "GK";
                }else {
                    ckMenjiang.setTextColor(Color.parseColor("#000000"));
                }
            }
        });

        ckHouwei.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    ckZhongchang.setChecked(false);
                    ckQianfeng.setChecked(false);
                    ckMenjiang.setChecked(false);
                    position="DC";
                    settextAllcolor();
                    ckHouwei.setTextColor(Color.parseColor("#ffffff"));
                }else {
                    ckHouwei.setTextColor(Color.parseColor("#333333"));
                }
            }
        });

        ckZhongchang.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ckMenjiang.setChecked(false);
                    ckHouwei.setChecked(false);
                    ckQianfeng.setChecked(false);
                    position="CM";
                    settextAllcolor();
                    ckZhongchang.setTextColor(Color.parseColor("#ffffff"));
                }else {
                    ckZhongchang.setTextColor(Color.parseColor("#333333"));
                }
            }
        });

        ckQianfeng.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    ckMenjiang.setChecked(false);
                    ckHouwei.setChecked(false);
                    ckZhongchang.setChecked(false);
                    position="CF";
                    settextAllcolor();
                    ckQianfeng.setTextColor(Color.parseColor("#ffffff"));
                }else {
                    ckQianfeng.setTextColor(Color.parseColor("#333333"));
                }

            }
        });

    }

    public void settextAllcolor(){
        ckMenjiang.setTextColor(Color.parseColor("#333333"));
        ckZhongchang.setTextColor(Color.parseColor("#333333"));
        ckHouwei.setTextColor(Color.parseColor("#333333"));
        ckQianfeng.setTextColor(Color.parseColor("#333333"));
    }

    @Override
    public void onClick(View v) {

        List<Map<String, Object>> imageSelects = iconSelectHelper.getSelImageList();
        if (imageSelects.size() > 0) {
            final String imgUrl = (String) imageSelects.get(0).get("imgUrl");
            final Bitmap bmp = (Bitmap) imageSelects.get(0).get("img");
            AppGlobalTool.execNetRequest(new Runnable() {
                @Override
                public void run() {
                    NetSetting.uploadImage(bmp, imgUrl, "", 0, new NetSetting.IRemoteQueryCallback(){
                        @Override
                        public void onFinish(String data, JSONObject jsonObject) {
                            try {
                                final String imageAddress = jsonObject.getString("img_address");
                                image_photo = imageAddress;
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        SavePersonalData();
                                    }
                                });
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailed(Exception e) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ToastUtils.showLongToast(PersonalDetailsActivity.this, "上传头像失败");
                                }
                            });
                        }
                    });
                }
            });
        }else{
            SavePersonalData();
        }
    }


    static Integer _ToInt(String s) {
        if (s == null || s.length() < 1) {
            return null;
        }
        return Integer.parseInt(s);
    }
    /**\
     * 保存数据
     */
    private void SavePersonalData() {
        final String name = etName.getText().toString();
        String idCard = etIDCard.getText().toString();
        String location = etLocation.getText().toString();
        String phone = etTelephone.getText().toString();

        /**
         * 实时刷新minefragment里面的数据(fragment和activity之间的通信)
         */
        s_mainActivity = MainActivity.s_mainActivity;
        MineFragment fragment = (MineFragment) s_mainActivity.getSupportFragmentManager().findFragmentByTag("mineTag");
        View view = fragment.getView();
        ImageView img = (ImageView) view.findViewById(R.id.iv_mine_head_photo);
        TextView namefrag = (TextView) view.findViewById(R.id.tv_mine_player_name);
        if (image_photo!=null && image_photo.length()>0){
            ImageLoader1.getInstance().displayCricleImage(s_mainActivity,image_photo,img);
        }
        namefrag.setText(name);

//        Integer sex = _ToInt(etSex.getText().toString());
        Integer sex = 0;
        String s = etSex.getText().toString();
        if (s.equals("男")||s.equals("男生")||s.equals("男人")||s.equals("男士")){
            sex=0;
        }else if (s.equals("女")||s.equals("女生")||s.equals("女人")||s.equals("女士")){
            sex=1;
        }

        Integer height = _ToInt(etHeight.getText().toString());
        Integer weight = _ToInt(etWeight.getText().toString());
        String a = etAge.getText().toString();
        age = _ToInt(a);


        if (User.getInstance().getUser_photo()==null||User.getInstance().getUser_photo().length()<=0){
            if (image_photo==null||image_photo.length()<=0){
                Toast.makeText(PersonalDetailsActivity.this, "还没有上传头像", Toast.LENGTH_LONG).show();
                return;
            }
        }

        if (idCard != null && idCard.length() > 0) {
            try {
                boolean idCardVali = IDCardValidate(idCard);
                if (!idCardVali) {
                    etIDCard.setFocusable(true);
                    Toast.makeText(PersonalDetailsActivity.this, "身份证不正确", Toast.LENGTH_LONG).show();
                    return;
                }
            } catch (ParseException e) {
                e.printStackTrace();
                etIDCard.setFocusable(true);
                Toast.makeText(PersonalDetailsActivity.this, "身份证不正确", Toast.LENGTH_LONG).show();
                return;
            }
        }
        if (phone != null && phone.length() > 0) {
            if (!checkPhone(phone)) {
                etTelephone.setFocusable(true);
                Toast.makeText(PersonalDetailsActivity.this, "电话号码不正确", Toast.LENGTH_LONG).show();
                return;
            }
        }


        if (idCard==null||idCard.length()==0){
            Toast.makeText(PersonalDetailsActivity.this, "身份证不能为空", Toast.LENGTH_LONG).show();
            return;
        }
        if (name==null||name.length()==0){
            Toast.makeText(PersonalDetailsActivity.this, "姓名不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        if ((location != null || phone != null||sex>=0 || height != null || weight != null || age != null || position != null)&&idCard!=null&&idCard.length()>0&&name!=null&&name.length()>0) {
            String policyNo = etPolicyno.getText().toString();
            showProgressDialog("正在保存个人信息。。。");
            NetSCFBLogin.requestUpdateUserInfo(name, idCard, location, phone, sex, height, weight, age, position, image_photo, policyNo, new BaseCallback<String>() {
                @Override
                public void onBeforeRequest(Request request) {

                }

                @Override
                public void onFailure(Request request, Exception e) {

                }

                @Override
                public void onResponse(Response response) {

                }

                @Override
                public void onSuccess(Response response, String o) {
                    hideProgressDialog();
                    Toast.makeText(PersonalDetailsActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                    if(image_photo!=null && image_photo.length()>0){
                        User.getInstance().setUser_photo(image_photo);
                    }
                    if(name!=null && name.length()>0){
                        User.getInstance().setMember_name(name);
                    }
                    if (flag){
                        s_mainActivity.switchFragment(2, 0);
                        LoginActivity sLoginactivy = LoginActivity.sLoginactivy;
                        sLoginactivy.finish();
                    }
                    finish();
                }
                @Override
                public void onError(Response response, int code, Exception e) {
                }
            });
        }

    }

    /**
     * 读数据
     */
    private void initPersonalData() {
        NetSCFBLogin.requestGetUserInfo(new BaseCallback<String>() {
            @Override
            public void onBeforeRequest(Request request) {

            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onResponse(Response response) {

            }

            @Override
            public void onSuccess(Response response, String o) {
                PersonalInfoEntry infoEntry = JSON.parseObject(o, PersonalInfoEntry.class);
                PersonalInfoEntry.Person data = infoEntry.getData();
                if (data.getName() != null) {
                    etName.setText(data.getName());
                }
                if (data.getIdcard() != null) {
                    etIDCard.setText(data.getIdcard());
                }
                if (data.getAddress() != null) {
                    etLocation.setText(data.getAddress());
                }
                if (data.getPhone() != null) {
                    etTelephone.setText(data.getPhone());
                }
                if (data.getSex() != null) {
                    int s=Integer.valueOf(data.getSex());
                    if (s==0){
                        etSex.setText("男");
                    }else if (s==1){
                        etSex.setText("女");
                    }
                }
                if (data.getHeight() != null) {
                    etHeight.setText(data.getHeight() + "");
                }
                if (data.getWeight() != null) {
                    etWeight.setText(data.getWeight() + "");
                }
                if (data.getAge() != null) {
                    etAge.setText(data.getAge() + "");
                }

                if(data.getPolicyno()!=null){
                    etPolicyno.setText(data.getPolicyno());
                }

                String position = data.getPosition();
                if(position!=null) {
                    if (position.equals("GK")) {
                        ckMenjiang.setChecked(true);
                        ckMenjiang.setTextColor(Color.parseColor("#ffffff"));
                    } else if (position.equals("DC")) {
                        ckHouwei.setChecked(true);
                        ckHouwei.setTextColor(Color.parseColor("#ffffff"));
                    } else if (position.equals("CM")) {
                        ckZhongchang.setChecked(true);
                        ckZhongchang.setTextColor(Color.parseColor("#ffffff"));
                    } else if (position.equals("CF")) {
                        ckQianfeng.setChecked(true);
                        ckQianfeng.setTextColor(Color.parseColor("#ffffff"));
                    }
                }
            }

            @Override
            public void onError(Response response, int code, Exception e) {
            }
        });
    }

    /**
     * 判断是否是手机号
     *
     * @param phone
     * @return
     */
    public boolean checkPhone(String phone) {
        Pattern p = Pattern.compile("1\\d{10}");
        Matcher matcher = p.matcher(phone);
//
//        Pattern pattern = Pattern
//                .compile("^(11[0-9]|15[0-9]|153|15[6-9]|180|18[23]|18[5-9])\\d{8}$");
//        Matcher matcher = pattern.matcher(phone);

        if (matcher.matches()) {
            return true;
        }
        Toast.makeText(PersonalDetailsActivity.this, "手机号格式不正确", Toast.LENGTH_SHORT).show();
        return false;
    }


    public boolean IDCardValidate(String IDStr) throws ParseException {
        String errorInfo = "";// 记录错误信息
        String[] ValCodeArr = {"1", "0", "x", "9", "8", "7", "6", "5", "4",
                "3", "2"};
        String[] Wi = {"7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7",
                "9", "10", "5", "8", "4", "2"};
        String Ai = "";
        // ================ 号码的长度 15位或18位 ================
        if (IDStr.length() != 15 && IDStr.length() != 18) {
            errorInfo = "身份证号码长度应该为15位或18位。";
            Toast.makeText(PersonalDetailsActivity.this, errorInfo, Toast.LENGTH_SHORT).show();
            return false;
        }
        // =======================(end)========================

        // ================ 数字 除最后以为都为数字 ================
        if (IDStr.length() == 18) {
            Ai = IDStr.substring(0, 17);
        } else if (IDStr.length() == 15) {
            Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);
        }
        if (isNumeric(Ai) == false) {
            errorInfo = "身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。";
            Toast.makeText(PersonalDetailsActivity.this, errorInfo, Toast.LENGTH_SHORT).show();
            return false;
        }
        // =======================(end)========================

        // ================ 出生年月是否有效 ================
        String strYear = Ai.substring(6, 10);// 年份
        String strMonth = Ai.substring(10, 12);// 月份
        String strDay = Ai.substring(12, 14);// 月份
        if (isDataFormat(strYear + "-" + strMonth + "-" + strDay) == false) {
            errorInfo = "身份证生日无效。";
            Toast.makeText(PersonalDetailsActivity.this, errorInfo, Toast.LENGTH_SHORT).show();
            return false;
        }
        GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150
                    || (gc.getTime().getTime() - s.parse(
                    strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
                errorInfo = "身份证生日不在有效范围。";
                Toast.makeText(PersonalDetailsActivity.this, errorInfo, Toast.LENGTH_SHORT).show();
                return false;
            }
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
            errorInfo = "身份证月份无效";
            Toast.makeText(PersonalDetailsActivity.this, errorInfo, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
            errorInfo = "身份证日期无效";
            Toast.makeText(PersonalDetailsActivity.this, errorInfo, Toast.LENGTH_SHORT).show();
            return false;
        }
        // =====================(end)=====================

        // ================ 地区码时候有效 ================
        Hashtable h = GetAreaCode();
        if (h.get(Ai.substring(0, 2)) == null) {
            errorInfo = "身份证地区编码错误。";
            Toast.makeText(PersonalDetailsActivity.this, errorInfo, Toast.LENGTH_SHORT).show();
            return false;
        }
        // ==============================================

        // ================ 判断最后一位的值 ================
        int TotalmulAiWi = 0;
        for (int i = 0; i < 17; i++) {
            TotalmulAiWi = TotalmulAiWi
                    + Integer.parseInt(String.valueOf(Ai.charAt(i)))
                    * Integer.parseInt(Wi[i]);
        }
        int modValue = TotalmulAiWi % 11;
        String strVerifyCode = ValCodeArr[modValue];
        Ai = Ai + strVerifyCode;

        if (IDStr.length() == 18) {
            if (Ai.equals(IDStr) == false) {
                errorInfo = "身份证无效，不是合法的身份证号码";
                Toast.makeText(PersonalDetailsActivity.this, errorInfo, Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            return true;
        }
        // =====================(end)=====================
        return true;
    }

    /**
     * 功能：设置地区编码
     *
     * @return Hashtable 对象
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    private static Hashtable GetAreaCode() {
        Hashtable hashtable = new Hashtable();
        hashtable.put("11", "北京");
        hashtable.put("12", "天津");
        hashtable.put("13", "河北");
        hashtable.put("14", "山西");
        hashtable.put("15", "内蒙古");
        hashtable.put("21", "辽宁");
        hashtable.put("22", "吉林");
        hashtable.put("23", "黑龙江");
        hashtable.put("31", "上海");
        hashtable.put("32", "江苏");
        hashtable.put("33", "浙江");
        hashtable.put("34", "安徽");
        hashtable.put("35", "福建");
        hashtable.put("36", "江西");
        hashtable.put("37", "山东");
        hashtable.put("41", "河南");
        hashtable.put("42", "湖北");
        hashtable.put("43", "湖南");
        hashtable.put("44", "广东");
        hashtable.put("45", "广西");
        hashtable.put("46", "海南");
        hashtable.put("50", "重庆");
        hashtable.put("51", "四川");
        hashtable.put("52", "贵州");
        hashtable.put("53", "云南");
        hashtable.put("54", "西藏");
        hashtable.put("61", "陕西");
        hashtable.put("62", "甘肃");
        hashtable.put("63", "青海");
        hashtable.put("64", "宁夏");
        hashtable.put("65", "新疆");
        hashtable.put("71", "台湾");
        hashtable.put("81", "香港");
        hashtable.put("82", "澳门");
        hashtable.put("91", "国外");
        return hashtable;
    }


    /**
     * 功能：判断字符串是否为数字
     *
     * @param str
     * @return
     */
    private static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (isNum.matches()) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 验证日期字符串是否是YYYY-MM-DD格式
     *
     * @param str
     * @return
     */
    private static boolean isDataFormat(String str) {
        boolean flag = false;
        // String
        // regxStr="[1-9][0-9]{3}-[0-1][0-2]-((0[1-9])|([12][0-9])|(3[01]))";
        String regxStr = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";
        Pattern pattern1 = Pattern.compile(regxStr);
        Matcher isNo = pattern1.matcher(str);
        if (isNo.matches()) {
            flag = true;
        }
        return flag;
    }


    public void openCitySelect(){
        Intent intent = new Intent(this, CityPickerActivity.class);
        startActivityForResult(intent, CityPickerActivity.REQUEST_CODE_PICK_CITY);
    }

    /**
     * 返回结果
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==RESULT_OK && requestCode == CityPickerActivity.REQUEST_CODE_PICK_CITY){
            String citySelect = data.getStringExtra(CityPickerActivity.KEY_PICKED_CITY);
            etLocation.setText(citySelect);
        }
        iconSelectHelper.onActivityResult(requestCode,resultCode,data);

        super.onActivityResult(requestCode, resultCode, data);
    }



    public void showProgressDialog(String text){
        hideProgressDialog();
        progressShow = true;
        progressDialog = new ProgressDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                progressShow = false;
            }
        });

        progressDialog.setMessage(text);
        progressDialog.show();
    }

    public void hideProgressDialog(){
        if(progressDialog!=null){
            progressDialog.dismiss();
            progressDialog=null;
            progressShow=false;
        }
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}

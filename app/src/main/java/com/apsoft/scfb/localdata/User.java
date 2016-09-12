package com.apsoft.scfb.localdata;

public class User {
	public static User user;
	public String uid;
	public String user_name;
	public String shareNum;
	public String user_photo;
	//public String back_photo;
	//public String signature;
	public String user_sex;
	public String user_city;
	public boolean is_login = false;
	public String office_id = null;
	public String area_id = null;
	public String team_id = null;
	public String member_id = null;
	public String member_name = null;
	public String team_flag = null;
	public String team_office = null;
	public String team_leader = null;
	public String is_team_leader = null;
	public String member_flag = null;

	public boolean isMemberAccept(){
		if(member_flag == null || member_flag.equalsIgnoreCase("0")){
			return false;
		}
		return true;
	}
	public void setIs_team_leader(String is_team_leader) {
		this.is_team_leader = is_team_leader;
	}

	public String getTeam_office() {
		return team_office;
	}

	public String getIs_team_leader() {
		return is_team_leader;
	}

	public boolean isTeamLeader(){
		if(is_team_leader == null){
			return false;
		}
		if(is_team_leader.equalsIgnoreCase("1")){
			return true;
		}
		return false;
	}
	public void setTeam_office(String team_office) {
		this.team_office = team_office;
	}

	public boolean isTeamJoinMatch(){
//		if(team_flag == null){
//			return false;
//		}
//		if(team_flag.equalsIgnoreCase("1")){
//			return true;
//		}
		if(team_office == null){
			return false;
		}
		String curOffice = User.getInstance().getCurrentOffice();
		if(curOffice == null){
			return false;
		}
		if(team_office.equalsIgnoreCase(curOffice)){
			return true;
		}
		return false;
	}

	public String getTeam_flag() {
		return team_flag;
	}

	public void setTeam_flag(String team_flag) {
		this.team_flag = team_flag;
	}

	public String getMember_name() {
		return member_name;
	}

	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getTeam_id() {
		return team_id;
	}

	public void setTeam_id(String team_id) {
		this.team_id = team_id;
	}

	public String getArea_id() {
		return area_id;
	}

	public void setArea_id(String area_id) {
		this.area_id = area_id;
	}

	public String getOffice_id_set() {
		return office_id_set;
	}

	public void setOffice_id_set(String office_id_set) {
		this.office_id_set = office_id_set;
	}

	public String getOffice_id() {
		return office_id;
	}

	public String getCurrentOffice(){
		if(office_id_set != null){
			return office_id_set;
		}
		return office_id;
	}

	public void setOffice_id(String office_id) {
		this.office_id = office_id;
	}

	public String office_id_set = null;

	public void setIs_login(boolean b){
		is_login = b;
	}

	public boolean getIs_login(){
		return is_login;
	}

	public String getUid() {
		if(uid==null)
			return "";
		return uid;
	}
	
	public boolean isValidID(){
		if(uid==null){
			return false;
		}
		if(uid.length()<1){
			return false;
		}
		return true;
	}
	
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUser_name() {
		if(user_name==null)
			return "";
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getShareNum() {
		if(shareNum==null)
			return "";
		return shareNum;
	}
	public void setShareNum(String shareNum) {
		this.shareNum = shareNum;
	}
	public String getUser_photo() {
		if(user_photo==null)
			return "";
		return user_photo;
	}
	public void setUser_photo(String user_photo) {
		this.user_photo = user_photo;
	}
	/*
	public String getBack_photo() {
		if(back_photo==null)
			return "";
		return back_photo;
	}
	public void setBack_photo(String back_photo) {
		this.back_photo = back_photo;
	}
	public String getSignature() {
		if(signature==null)
			return "";
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}*/
	public User(){}
	public static User getInstance() {
		if(user==null)
			user=new User();
		return user; 
		
	}
	
	public void reset(){
		uid = "";
		team_id= "";
		user_name= "";
		user_sex = null;
		user_city = null;
		is_login = false;
	}
}

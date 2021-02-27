package kr.or.connect.vaccine.dto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Member {

	private int memberId; //자동으로 증가되는 primary key 값
	private String memberAge; // 나이 추후에 20~25 , 26~30 등으로 세분화 할 예정
	private String memberSex; // 성별 "남성", "여성", "남자", "여자" 필요한 
	private String memberVac; // 어떤 백신을 맞을 것인지 현재는 숫자형으로 저장됨. 추후 매칭 필요
	private Date memberDate; // 백신을 맞은 날짜를 의미함 자동으로 현재 Date 값이 들어가게 됨.
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public String getMemberAge() {
		return memberAge;
	}
	public void setMemberAge(String memberAge) {
		this.memberAge = memberAge;
	}
	public String getMemberSex() {
		return memberSex;
	}
	public void setMemberSex(String memberSex) {
		this.memberSex = memberSex;
	}
	public String getMemberVac() {
		return memberVac;
	}
	public void setMemberVac(String memberVac) {
		this.memberVac = memberVac;
	}
	public Date getMemberDate() {
		return memberDate;
	}
	public void setMemberDate(java.util.Date date) {
		this.memberDate = date;
	}
	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", memberAge=" + memberAge + ", memberSex=" + memberSex + ", memberVac="
				+ memberVac + ", memberDate=" + memberDate + "]";
	}

	
	
	
}

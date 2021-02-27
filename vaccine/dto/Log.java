package kr.or.connect.vaccine.dto;

import java.util.Date;

public class Log {
	private Long id; //로그 고유 id 자동으로 증가된다.
	private String ip; // 어떤 사용자가 들어왔는지 해당 ip
	private String method; // http 방식의 post인지 get인지 등의 method를 나열시킨다.
	private Date regdate; // 실제 데이터를 넣은 날짜. 자동으로 java에서 등록한다.
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "Log [id=" + id + ", ip=" + ip + ", method=" + method + ", regdate=" + regdate + "]";
	}
}

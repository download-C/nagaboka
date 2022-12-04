package com.nagaboka.domain;

import java.sql.Timestamp;
import java.util.List;

import lombok.Data;

@Data
public class MemberVO {
	
	private String user_id;
	private String user_pw;
	private String user_name;
	private String user_phone;
	private Timestamp reg_date;
	private Double latitude;
	private Double longitude;
	private String road_full_addr;
	private String enabled;
	
	private List<AuthVO> authList;
	
}

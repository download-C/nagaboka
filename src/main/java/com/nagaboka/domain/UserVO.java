package com.nagaboka.domain;

import java.sql.Timestamp;
import java.util.List;

import lombok.Data;

@Data
public class UserVO {
	
	private String u_id;
	private String u_pw;
	private String u_name;
	private String u_phone;
	private Timestamp u_reg_date;
	private Double u_latitude;
	private Double u_longitude;
	private String u_road_full_addr;
	private String u_enabled;
	
	private List<UserAuthVO> authList;
	
}

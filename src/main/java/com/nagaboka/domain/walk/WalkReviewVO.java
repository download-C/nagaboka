package com.nagaboka.domain.walk;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class WalkReviewVO {
	private int wrno;
	private WalkVO walk;
	private String user_id;
	private Timestamp wrregdate;
	private String wrcon;;
	private String wrimgs;
	private boolean wrlike;	

}

package com.nagaboka.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class WalkReviewVO {
	private int wcno;
	private WalkVO walk;
	private String username;
	private Timestamp wcregdate;
	private String wccon;;
	private String wcimgs;
	private boolean wclike;	

}

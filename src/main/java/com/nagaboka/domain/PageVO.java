package com.nagaboka.domain;

import lombok.Data;

@Data
public class PageVO {
	private String pageNum;		// 현재 보고있는 페이지
	private int pageSize;		// 페이지 하나에 보여지는 글 개수
	private int pageBlock;		// 화면 아래에 한 번에 이동할 수 있는 페이지 개수
	private int CurrentPage;	// 현재 보고있는 페이지
	private int startPage;		// 현재 페이지에서 블록 첫 페이지 -> (endPage-pageBlock)+1
	private int endPage;		// 현재 페이지에서 블록 끝 페이지 -> ((page/pageBlock)+1)*pageBlock
	private int pageCount;		// 뭐더라
	private int startRow;		// DB에서 현 페이지 맨 처음에 나올 거
	private int endRow;			// DB에서 현 페이지 젤 마지막에 나올 거
	private String search;      // 검색어
}

package com.nagaboka.domain;

import lombok.Data;

@Data
public class PageVO {
		
	private int page; // 페이지
	private int perPageNum; // 한번에 보여줄 페이지 수

	public PageVO() {
		this.page = 1;
		this.perPageNum = 10;
	}

	public void setPage(int page) {
		if (page <= 0) { 
			this.page = 1; 
			return; 
		}
		this.page = page;
	}
			
	public int getPerPageNum() {
		return perPageNum;
	}

	public void setPerPageNum(int perPageNum) {
		if (perPageNum < 10 || perPageNum > 100) {
			this.perPageNum = 10;
			return;
		}
		this.perPageNum = perPageNum;
	}

	public int getPageStart() {

		return (this.page - 1) * perPageNum;
	}
			
}

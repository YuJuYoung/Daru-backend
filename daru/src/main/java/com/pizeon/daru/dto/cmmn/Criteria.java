package com.pizeon.daru.dto.cmmn;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Criteria {
	
	private int pageNum;
	private int rowCnt;
	private String keyword;
	
	public Criteria() {
		this.pageNum = 1;
		this.rowCnt = 10;
	}

}

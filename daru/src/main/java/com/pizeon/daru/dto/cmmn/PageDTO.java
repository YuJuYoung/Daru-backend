package com.pizeon.daru.dto.cmmn;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class PageDTO<T> {
	
	private int totalPage;
	private int pageNum;
	private String keyword;
	private List<T> content;

}

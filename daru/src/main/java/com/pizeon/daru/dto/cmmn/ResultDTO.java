package com.pizeon.daru.dto.cmmn;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResultDTO<T> {
	
	private boolean success;
	private String message;
	private T data;
	
}

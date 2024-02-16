package com.pizeon.daru.dto.reqDocInfo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReqDocInfoCreateDTO {
	
	private String explainTxt;
	private Integer minLen;
	private Integer maxLen;

}

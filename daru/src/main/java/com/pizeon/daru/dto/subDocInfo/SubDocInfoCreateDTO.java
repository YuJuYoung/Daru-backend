package com.pizeon.daru.dto.subDocInfo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SubDocInfoCreateDTO {
	
	private String explainTxt;
	private String content;

}

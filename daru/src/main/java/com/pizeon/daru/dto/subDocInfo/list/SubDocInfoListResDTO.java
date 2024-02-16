package com.pizeon.daru.dto.subDocInfo.list;

import com.pizeon.daru.domain.SubDocInfo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SubDocInfoListResDTO {
	
	private String explainTxt;
	private String content;
	
	public static SubDocInfoListResDTO fromEntity(SubDocInfo subDocInfo) {
		return builder()
				.explainTxt(subDocInfo.getExplainTxt())
				.content(subDocInfo.getContent())
				.build();
	}

}

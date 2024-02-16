package com.pizeon.daru.dto.reqDocInfo;

import com.pizeon.daru.domain.ReqDocInfo;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder(access = AccessLevel.PRIVATE)
public class ReqDocInfoListDTO {
	
	private Long id;
	private String explainTxt;
	private Integer minLen;
	private Integer maxLen;
	
	public static ReqDocInfoListDTO fromEntity(ReqDocInfo reqDocInfo) {
		return builder()
				.id(reqDocInfo.getId())
				.explainTxt(reqDocInfo.getExplainTxt())
				.minLen(reqDocInfo.getMinLen())
				.maxLen(reqDocInfo.getMaxLen())
				.build();
	}

}

package com.pizeon.daru.dto.subDoc.list;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SubDocListReqDTO {
	
	private Long postWriterId;
	private SubDocCriteriaDTO subDocCriteria;

}

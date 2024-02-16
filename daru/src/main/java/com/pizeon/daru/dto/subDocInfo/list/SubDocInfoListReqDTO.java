package com.pizeon.daru.dto.subDocInfo.list;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SubDocInfoListReqDTO {
	
	private Long subDocId;
	private Long loginedId;

}

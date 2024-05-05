package com.pizeon.daru.dto.subDoc.list;

import com.pizeon.daru.dto.cmmn.Criteria;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SubDocListReqDTO {
	
	private Long postWriterId;
	private Long postId;
	private Criteria criteria;

}

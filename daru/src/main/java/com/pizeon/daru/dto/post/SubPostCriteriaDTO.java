package com.pizeon.daru.dto.post;

import com.pizeon.daru.dto.cmmn.Criteria;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SubPostCriteriaDTO {
	
	private Long userId;
	private Criteria criteria;

}

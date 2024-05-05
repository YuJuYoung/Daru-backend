package com.pizeon.daru.dto.reqDocInfo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReqDocInfoCreateDTO {
	
	@NotBlank
	private String explainTxt;
	
	@PositiveOrZero
	private Integer minLen;
	
	@Positive
	private Integer maxLen;

}

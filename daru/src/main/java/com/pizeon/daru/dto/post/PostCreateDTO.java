package com.pizeon.daru.dto.post;

import java.util.List;

import com.pizeon.daru.dto.reqDocInfo.ReqDocInfoCreateDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostCreateDTO {
	
	@NotBlank
	private String title;
	
	@NotBlank
	private String description;
	
	private Long writerId;
	
	@Valid
	private List<ReqDocInfoCreateDTO> reqDocInfoList;

}

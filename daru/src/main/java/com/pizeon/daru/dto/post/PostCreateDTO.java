package com.pizeon.daru.dto.post;

import java.time.LocalDateTime;
import java.util.List;

import com.pizeon.daru.dto.reqDocInfo.ReqDocInfoCreateDTO;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostCreateDTO {
	
	private Long id;
	private String title;
	private String description;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private Long writerId;
	private List<ReqDocInfoCreateDTO> reqDocInfoList;

}

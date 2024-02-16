package com.pizeon.daru.dto.subDoc;

import java.time.LocalDateTime;
import java.util.List;

import com.pizeon.daru.dto.subDocInfo.SubDocInfoCreateDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SubDocCreateDTO {
	
	private Long postId;
	private Long userId;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private List<SubDocInfoCreateDTO> subDocInfoList;

}

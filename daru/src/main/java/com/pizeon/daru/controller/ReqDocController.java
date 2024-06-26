package com.pizeon.daru.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pizeon.daru.dto.cmmn.ResultDTO;
import com.pizeon.daru.dto.reqDocInfo.ReqDocInfoListResDTO;
import com.pizeon.daru.service.ReqDocService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/req-doc")
public class ReqDocController {
	
	private final ReqDocService reqDocService;
	
	@PostMapping("/info/list")
	public ResultDTO<List<ReqDocInfoListResDTO>> infoList(@RequestBody String postId) {
		try {
			return ResultDTO.<List<ReqDocInfoListResDTO>>builder()
					.success(true)
					.data(reqDocService.infoList(Long.parseLong(postId)))
					.build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResultDTO.<List<ReqDocInfoListResDTO>>builder()
				.success(false)
				.message("양식 정보를 불러오는데 실패했습니다.")
				.build();
	}

}

package com.pizeon.daru.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pizeon.daru.dto.cmmn.ResultDTO;
import com.pizeon.daru.dto.reqDocInfo.ReqDocInfoListDTO;
import com.pizeon.daru.service.ReqDocService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/req-doc")
public class ReqDocController {
	
	private final ReqDocService reqDocService;
	
	@PostMapping("/info/list")
	public ResultDTO<List<ReqDocInfoListDTO>> infoList(@RequestBody String postId) {
		return ResultDTO.<List<ReqDocInfoListDTO>>builder()
				.success(true)
				.data(reqDocService.infoList(Long.parseLong(postId)))
				.build();
	}

}

package com.pizeon.daru.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pizeon.daru.dto.cmmn.PageDTO;
import com.pizeon.daru.dto.cmmn.ResultDTO;
import com.pizeon.daru.dto.subDoc.SubDocCreateDTO;
import com.pizeon.daru.dto.subDoc.list.SubDocListReqDTO;
import com.pizeon.daru.dto.subDoc.list.SubDocListResDTO;
import com.pizeon.daru.dto.subDocInfo.list.SubDocInfoListReqDTO;
import com.pizeon.daru.dto.subDocInfo.list.SubDocInfoListResDTO;
import com.pizeon.daru.service.SubDocService;
import com.pizeon.daru.util.HttpSessionUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sub-doc")
public class SubDocController {
	
	private final SubDocService subDocService;
	private final HttpSessionUtil httpSessionUtil;
	
	@PostMapping("/create")
	public ResultDTO<?> create(HttpServletRequest request, @Valid @RequestBody SubDocCreateDTO subDocCreateDTO) {
		try {
			if (httpSessionUtil.isLoginedId(request.getSession(), subDocCreateDTO.getUserId())) {
				boolean createResult = subDocService.create(subDocCreateDTO);
				
				if (createResult) {
					return ResultDTO.builder()
							.success(true)
							.build();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResultDTO.builder()
				.success(false)
				.message("오류가 발생했습니다.")
				.build();
	}
	
	
	
	@PostMapping("/list")
	public ResultDTO<PageDTO<SubDocListResDTO>> list(HttpServletRequest request, @RequestBody SubDocListReqDTO subDocListReqDTO) {
		try {
			if (httpSessionUtil.isLoginedId(request.getSession(), subDocListReqDTO.getPostWriterId())) {
				Optional<PageDTO<SubDocListResDTO>> pageDTO = subDocService.list(subDocListReqDTO);
				
				if (!pageDTO.isEmpty()) {
					return ResultDTO.<PageDTO<SubDocListResDTO>>builder()
							.success(true)
							.data(pageDTO.get())
							.build();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResultDTO.<PageDTO<SubDocListResDTO>>builder()
				.success(false)
				.message("서류 목록을 불러오는데 실패했습니다.")
				.build();
	}
	
	@PostMapping("/info/list")
	public ResultDTO<List<SubDocInfoListResDTO>> infoList(HttpServletRequest request, @RequestBody SubDocInfoListReqDTO subDocInfoListReqDTO) {
		try {
			if (httpSessionUtil.isLoginedId(request.getSession(), subDocInfoListReqDTO.getLoginedId())) {
				return ResultDTO.<List<SubDocInfoListResDTO>>builder()
						.success(true)
						.data(subDocService.infoList(subDocInfoListReqDTO).get())
						.build();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResultDTO.<List<SubDocInfoListResDTO>>builder()
				.success(false)
				.message("목록을 불러오는데 실패했습니다.")
				.build();
	}

}

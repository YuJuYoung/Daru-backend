package com.pizeon.daru.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pizeon.daru.dto.cmmn.Criteria;
import com.pizeon.daru.dto.cmmn.PageDTO;
import com.pizeon.daru.dto.cmmn.ResultDTO;
import com.pizeon.daru.dto.post.MyPostCriteriaDTO;
import com.pizeon.daru.dto.post.PostCreateDTO;
import com.pizeon.daru.dto.post.PostDetailDTO;
import com.pizeon.daru.dto.post.PostListDTO;
import com.pizeon.daru.dto.post.SubPostCriteriaDTO;
import com.pizeon.daru.service.PostService;
import com.pizeon.daru.util.HttpSessionUtil;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {
	
	private final PostService postService;
	
	@PostMapping("/list")
	public ResultDTO<PageDTO<PostListDTO>> list(@RequestBody Criteria criteria) {
		try {
			PageDTO<PostListDTO> pageDTO = postService.list(criteria);
			
			if (pageDTO != null) {
				return ResultDTO.<PageDTO<PostListDTO>>builder()
						.success(true)
						.data(pageDTO)
						.build();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResultDTO.<PageDTO<PostListDTO>>builder()
				.success(false)
				.message("글 목록 불러오기에 실패했습니다.")
				.build();
	}
	
	@PostMapping("/my-list")
	public ResultDTO<PageDTO<PostListDTO>> myList(HttpServletRequest request, @RequestBody MyPostCriteriaDTO myPostCriteriaDTO) {
		try {
			if (HttpSessionUtil.isLoginedId(request.getSession(), myPostCriteriaDTO.getUserId())) {
				PageDTO<PostListDTO> pageDTO = postService.myList(myPostCriteriaDTO);
				
				if (pageDTO != null) {
					return ResultDTO.<PageDTO<PostListDTO>>builder()
							.success(true)
							.data(pageDTO)
							.build();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResultDTO.<PageDTO<PostListDTO>>builder()
				.success(false)
				.message("글 목록 불러오기에 실패했습니다.")
				.build();
	}
	
	@PostMapping("/sub/list")
	public ResultDTO<PageDTO<PostListDTO>> subList(HttpServletRequest request, @RequestBody SubPostCriteriaDTO subPostCriteriaDTO) {
		try {
			if (HttpSessionUtil.isLoginedId(request.getSession(), subPostCriteriaDTO.getUserId())) {
				PageDTO<PostListDTO> pageDTO = postService.submittedList(subPostCriteriaDTO);
				
				if (pageDTO != null) {
					return ResultDTO.<PageDTO<PostListDTO>>builder()
							.success(true)
							.data(pageDTO)
							.build();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResultDTO.<PageDTO<PostListDTO>>builder()
				.success(false)
				.message("글 목록 불러오기에 실패했습니다.")
				.build();
	}
	
	@GetMapping("/{postId}/detail")
	public ResultDTO<PostDetailDTO> detail(@PathVariable("postId") Long postId) {
		PostDetailDTO postDetailDTO = postService.detail(postId);
		
		if (postDetailDTO != null) {
			return ResultDTO.<PostDetailDTO>builder()
					.success(true)
					.data(postDetailDTO)
					.build();
		}
		return ResultDTO.<PostDetailDTO>builder()
				.success(false)
				.message("글 불러오기에 실패했습니다.")
				.build();
	}
	
	@PostMapping("/create")
	public ResultDTO<Long> create(HttpServletRequest request, @RequestBody PostCreateDTO postCreateDTO) {
		Long writerId = postCreateDTO.getWriterId();
		
		if (HttpSessionUtil.isLoginedId(request.getSession(), writerId)) {
			Long postId = postService.create(postCreateDTO);
			
			if (postId != null) {
				return ResultDTO.<Long>builder()
						.success(true)
						.data(postId)
						.build();
			}
		}
		return ResultDTO.<Long>builder()
				.success(false)
				.message("글 등록에 실패했습니다.")
				.build();
	}

}

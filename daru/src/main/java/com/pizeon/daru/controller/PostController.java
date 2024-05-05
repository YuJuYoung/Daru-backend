package com.pizeon.daru.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pizeon.daru.dto.cmmn.Criteria;
import com.pizeon.daru.dto.cmmn.PageDTO;
import com.pizeon.daru.dto.cmmn.ResultDTO;
import com.pizeon.daru.dto.post.PostCreateDTO;
import com.pizeon.daru.dto.post.PostDetailDTO;
import com.pizeon.daru.dto.post.list.MyPostListDTO;
import com.pizeon.daru.dto.post.list.PostListDTO;
import com.pizeon.daru.dto.post.list.SubPostListDTO;
import com.pizeon.daru.service.PostService;
import com.pizeon.daru.util.HttpSessionUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {
	
	private final PostService postService;
	private final HttpSessionUtil httpSessionUtil;
	
	@PostMapping("/list")
	public ResultDTO<PageDTO<PostListDTO>> list(@RequestBody Criteria criteria) {
		try {
			Optional<PageDTO<PostListDTO>> pageDTO = postService.list(criteria);
			
			if (!pageDTO.isEmpty()) {
				return ResultDTO.<PageDTO<PostListDTO>>builder()
						.success(true)
						.data(pageDTO.get())
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
	public ResultDTO<PageDTO<PostListDTO>> myList(HttpServletRequest request, @RequestBody MyPostListDTO myPostListDTO) {
		try {
			if (httpSessionUtil.isLoginedId(request.getSession(), myPostListDTO.getUserId())) {
				Optional<PageDTO<PostListDTO>> pageDTO = postService.myList(myPostListDTO);
				
				if (!pageDTO.isEmpty()) {
					return ResultDTO.<PageDTO<PostListDTO>>builder()
							.success(true)
							.data(pageDTO.get())
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
	public ResultDTO<PageDTO<PostListDTO>> subList(HttpServletRequest request, @RequestBody SubPostListDTO subPostListDTO) {
		try {
			if (httpSessionUtil.isLoginedId(request.getSession(), subPostListDTO.getUserId())) {
				Optional<PageDTO<PostListDTO>> pageDTO = postService.submittedList(subPostListDTO);
				
				if (!pageDTO.isEmpty()) {
					return ResultDTO.<PageDTO<PostListDTO>>builder()
							.success(true)
							.data(pageDTO.get())
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
		try {
			Optional<PostDetailDTO> postDetailDTO = postService.detail(postId);
			
			if (postDetailDTO != null) {
				return ResultDTO.<PostDetailDTO>builder()
						.success(true)
						.data(postDetailDTO.get())
						.build();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResultDTO.<PostDetailDTO>builder()
				.success(false)
				.message("글 불러오기에 실패했습니다.")
				.build();
	}
	
	@PostMapping("/create")
	public ResultDTO<Long> create(HttpServletRequest request, @Valid @RequestBody PostCreateDTO postCreateDTO) {
		try {
			Long writerId = postCreateDTO.getWriterId();
			
			if (httpSessionUtil.isLoginedId(request.getSession(), writerId)) {
				Optional<Long> postId = postService.create(postCreateDTO);
				
				if (!postId.isEmpty()) {
					return ResultDTO.<Long>builder()
							.success(true)
							.data(postId.get())
							.build();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResultDTO.<Long>builder()
				.success(false)
				.message("글 등록에 실패했습니다.")
				.build();
	}

}

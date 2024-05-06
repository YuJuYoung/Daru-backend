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
import com.pizeon.daru.dto.post.PostDetailResDTO;
import com.pizeon.daru.dto.post.list.PostListResDTO;
import com.pizeon.daru.dto.post.list.myPost.MyPostListReqDTO;
import com.pizeon.daru.dto.post.list.subPost.SubPostListReqDTO;
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
	public ResultDTO<PageDTO<PostListResDTO>> list(@RequestBody Criteria criteria) {
		try {
			Optional<PageDTO<PostListResDTO>> pageDTO = postService.list(criteria);
			
			if (!pageDTO.isEmpty()) {
				return ResultDTO.<PageDTO<PostListResDTO>>builder()
						.success(true)
						.data(pageDTO.get())
						.build();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResultDTO.<PageDTO<PostListResDTO>>builder()
				.success(false)
				.message("글 목록 불러오기에 실패했습니다.")
				.build();
	}
	
	@PostMapping("/my-list")
	public ResultDTO<PageDTO<PostListResDTO>> myList(HttpServletRequest request, @RequestBody MyPostListReqDTO myPostListReqDTO) {
		try {
			if (httpSessionUtil.isLoginedId(request.getSession(), myPostListReqDTO.getUserId())) {
				Optional<PageDTO<PostListResDTO>> pageDTO = postService.myList(myPostListReqDTO);
				
				if (!pageDTO.isEmpty()) {
					return ResultDTO.<PageDTO<PostListResDTO>>builder()
							.success(true)
							.data(pageDTO.get())
							.build();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResultDTO.<PageDTO<PostListResDTO>>builder()
				.success(false)
				.message("글 목록 불러오기에 실패했습니다.")
				.build();
	}
	
	@PostMapping("/sub/list")
	public ResultDTO<PageDTO<PostListResDTO>> subList(HttpServletRequest request, @RequestBody SubPostListReqDTO subPostListReqDTO) {
		try {
			if (httpSessionUtil.isLoginedId(request.getSession(), subPostListReqDTO.getUserId())) {
				Optional<PageDTO<PostListResDTO>> pageDTO = postService.submittedList(subPostListReqDTO);
				
				if (!pageDTO.isEmpty()) {
					return ResultDTO.<PageDTO<PostListResDTO>>builder()
							.success(true)
							.data(pageDTO.get())
							.build();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResultDTO.<PageDTO<PostListResDTO>>builder()
				.success(false)
				.message("글 목록 불러오기에 실패했습니다.")
				.build();
	}
	
	@GetMapping("/{postId}/detail")
	public ResultDTO<PostDetailResDTO> detail(@PathVariable("postId") Long postId) {
		try {
			Optional<PostDetailResDTO> postDetailResDTO = postService.detail(postId);
			
			if (!postDetailResDTO.isEmpty()) {
				return ResultDTO.<PostDetailResDTO>builder()
						.success(true)
						.data(postDetailResDTO.get())
						.build();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResultDTO.<PostDetailResDTO>builder()
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

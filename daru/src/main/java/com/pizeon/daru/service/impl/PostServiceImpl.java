package com.pizeon.daru.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pizeon.daru.domain.Post;
import com.pizeon.daru.domain.ReqDocInfo;
import com.pizeon.daru.domain.SubDoc;
import com.pizeon.daru.domain.User;
import com.pizeon.daru.dto.cmmn.Criteria;
import com.pizeon.daru.dto.cmmn.PageDTO;
import com.pizeon.daru.dto.post.PostCreateDTO;
import com.pizeon.daru.dto.post.PostDetailResDTO;
import com.pizeon.daru.dto.post.list.MyPostListReqDTO;
import com.pizeon.daru.dto.post.list.PostListResDTO;
import com.pizeon.daru.dto.post.list.SubPostListReqDTO;
import com.pizeon.daru.dto.reqDocInfo.ReqDocInfoCreateDTO;
import com.pizeon.daru.repository.PostRepository;
import com.pizeon.daru.repository.ReqDocInfoRepository;
import com.pizeon.daru.repository.SubDocRepository;
import com.pizeon.daru.repository.UserRepository;
import com.pizeon.daru.service.PostService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
	
	private final PostRepository postRepository;
	private final UserRepository userRepository;
	private final ReqDocInfoRepository reqDocInfoRepository;
	private final SubDocRepository subDocRepository;
			
	@Override
	public Optional<PageDTO<PostListResDTO>> list(Criteria criteria) throws Exception {
		Pageable pageable = PageRequest.of(criteria.getPageNum() - 1, criteria.getRowCnt());
		Page<Post> page =
				postRepository.findByTitleContainsOrderByCreatedAtDesc(criteria.getKeyword(), pageable);
		
		return Optional.of(
				PageDTO.<PostListResDTO>builder()
				.totalPage(page.getTotalPages())
				.pageNum(page.getPageable().getPageNumber() + 1)
				.keyword(criteria.getKeyword())
				.content(page.get().map(post -> PostListResDTO.fromEntity(post)).toList())
				.build());
	}
	
	@Override
	public Optional<PageDTO<PostListResDTO>> myList(MyPostListReqDTO myPostListReqDTO) throws Exception {
		Long userId = myPostListReqDTO.getUserId();
		Criteria criteria = myPostListReqDTO.getCriteria();
		
		User writer = userRepository.findById(userId).get();
		Pageable pageable = PageRequest.of(criteria.getPageNum() - 1, criteria.getRowCnt());
		Page<Post> page =
				postRepository.findByTitleContainsAndWriterOrderByCreatedAtDesc(criteria.getKeyword(), writer, pageable);
		
		return Optional.of(
				PageDTO.<PostListResDTO>builder()
				.totalPage(page.getTotalPages())
				.pageNum(page.getPageable().getPageNumber() + 1)
				.content(page.get().map(post -> PostListResDTO.fromEntity(post)).toList())
				.build());
	}
	
	@Override
	public Optional<PageDTO<PostListResDTO>> submittedList(SubPostListReqDTO subPostListReqDTO) {
		Long userId = subPostListReqDTO.getUserId();
		Criteria criteria = subPostListReqDTO.getCriteria();
		
		User writer = userRepository.findById(userId).get();
		Pageable pageable = PageRequest.of(criteria.getPageNum() - 1, criteria.getRowCnt());
		Page<SubDoc> page = subDocRepository.findByUser(writer, pageable);
		
		return Optional.of(
				PageDTO.<PostListResDTO>builder()
				.totalPage(page.getTotalPages())
				.pageNum(page.getPageable().getPageNumber() + 1)
				.content(page.get().map(subDoc -> PostListResDTO.fromEntity(subDoc.getPost())).toList())
				.build());
	}
	
	@Override
	public Optional<PostDetailResDTO> detail(Long postId) {
		Optional<Post> post = postRepository.findById(postId);
		
		if (!post.isEmpty()) {
			return Optional.of(PostDetailResDTO.fromEntity(post.get()));
		}
		return Optional.empty();
	}

	@Override
	public Optional<Long> create(PostCreateDTO postCreateDTO) {
		User writer = userRepository.findById(postCreateDTO.getWriterId()).get();
		
		if (writer != null) {
			LocalDateTime now = LocalDateTime.now();
			Post post = postRepository.save(Post.fromCreateDTO(postCreateDTO, writer, now));
			
			if (post != null) {
				for (ReqDocInfoCreateDTO reqDocInfoCreateDTO : postCreateDTO.getReqDocInfoList()) {
					ReqDocInfo reqDocInfo =
							reqDocInfoRepository.save(ReqDocInfo.fromCreateDTO(reqDocInfoCreateDTO, post));
					
					if (reqDocInfo == null) {
						postRepository.delete(post);
						return null;
					}
				}
				return Optional.of(post.getId());
			}
		}
		return Optional.empty();
	}

}

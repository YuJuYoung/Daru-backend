package com.pizeon.daru.service.impl;

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
import com.pizeon.daru.dto.post.MyPostCriteriaDTO;
import com.pizeon.daru.dto.post.PostCreateDTO;
import com.pizeon.daru.dto.post.PostDetailDTO;
import com.pizeon.daru.dto.post.PostListDTO;
import com.pizeon.daru.dto.post.SubPostCriteriaDTO;
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
	public PageDTO<PostListDTO> list(Criteria criteria) {
		try {
			Pageable pageable = PageRequest.of(criteria.getPageNum() - 1, criteria.getRowCnt());
			Page<Post> page =
					postRepository.findByTitleContainsOrderByCreatedAtDesc(criteria.getKeyword(), pageable);
			
			return PageDTO.<PostListDTO>builder()
					.totalPage(page.getTotalPages())
					.pageNum(page.getPageable().getPageNumber() + 1)
					.keyword(criteria.getKeyword())
					.content(page.get().map(post -> PostListDTO.fromEntity(post)).toList())
					.build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public PageDTO<PostListDTO> myList(MyPostCriteriaDTO myPostCriteriaDTO) {
		try {
			Long userId = myPostCriteriaDTO.getUserId();
			Criteria criteria = myPostCriteriaDTO.getCriteria();
			
			User writer = userRepository.findById(userId).get();
			Pageable pageable = PageRequest.of(criteria.getPageNum() - 1, criteria.getRowCnt());
			Page<Post> page =
					postRepository.findByTitleContainsAndWriterOrderByCreatedAtDesc(criteria.getKeyword(), writer, pageable);
			
			return PageDTO.<PostListDTO>builder()
					.totalPage(page.getTotalPages())
					.pageNum(page.getPageable().getPageNumber() + 1)
					.content(page.get().map(post -> PostListDTO.fromEntity(post)).toList())
					.build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public PageDTO<PostListDTO> submittedList(SubPostCriteriaDTO subPostCriteriaDTO) {
		try {
			Long userId = subPostCriteriaDTO.getUserId();
			Criteria criteria = subPostCriteriaDTO.getCriteria();
			
			User writer = userRepository.findById(userId).get();
			Pageable pageable = PageRequest.of(criteria.getPageNum() - 1, criteria.getRowCnt());
			Page<SubDoc> page = subDocRepository.findByUser(writer, pageable);
			
			return PageDTO.<PostListDTO>builder()
					.totalPage(page.getTotalPages())
					.pageNum(page.getPageable().getPageNumber() + 1)
					.content(page.get().map(subDoc -> PostListDTO.fromEntity(subDoc.getPost())).toList())
					.build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public PostDetailDTO detail(Long postId) {
		return PostDetailDTO.fromEntity(postRepository.findById(postId).get());
	}

	@Override
	public Long create(PostCreateDTO postCreateDTO) {
		User writer = userRepository.findById(postCreateDTO.getWriterId()).get();
		
		if (writer != null) {
			Post post = postRepository.save(Post.fromCreateDTO(postCreateDTO, writer));
			
			if (post != null) {
				for (ReqDocInfoCreateDTO reqDocInfoCreateDTO : postCreateDTO.getReqDocInfoList()) {
					ReqDocInfo reqDocInfo =
							reqDocInfoRepository.save(ReqDocInfo.fromCreateDTO(reqDocInfoCreateDTO, post));
					
					if (reqDocInfo == null) {
						postRepository.delete(post);
						return null;
					}
				}
				return post.getId();
			}
		}
		return null;
	}

}

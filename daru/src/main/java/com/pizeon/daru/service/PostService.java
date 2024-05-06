package com.pizeon.daru.service;

import java.util.Optional;

import com.pizeon.daru.dto.cmmn.Criteria;
import com.pizeon.daru.dto.cmmn.PageDTO;
import com.pizeon.daru.dto.post.PostCreateDTO;
import com.pizeon.daru.dto.post.PostDetailResDTO;
import com.pizeon.daru.dto.post.PostListResDTO;
import com.pizeon.daru.dto.post.myPost.MyPostListReqDTO;
import com.pizeon.daru.dto.post.subPost.SubPostListReqDTO;

public interface PostService {
	
	public Optional<PageDTO<PostListResDTO>> list(Criteria criteria) throws Exception;
	public Optional<PageDTO<PostListResDTO>> myList(MyPostListReqDTO myPostListReqDTO) throws Exception;
	public Optional<PageDTO<PostListResDTO>> submittedList(SubPostListReqDTO subPostListReqDTO) throws Exception;
	public Optional<PostDetailResDTO> detail(Long postId) throws Exception;
	public Optional<Long> create(PostCreateDTO postCreateDTO) throws Exception;

}

package com.pizeon.daru.service;

import java.util.Optional;

import com.pizeon.daru.dto.cmmn.Criteria;
import com.pizeon.daru.dto.cmmn.PageDTO;
import com.pizeon.daru.dto.post.PostCreateDTO;
import com.pizeon.daru.dto.post.PostDetailDTO;
import com.pizeon.daru.dto.post.list.MyPostListDTO;
import com.pizeon.daru.dto.post.list.PostListDTO;
import com.pizeon.daru.dto.post.list.SubPostListDTO;

public interface PostService {
	
	public Optional<PageDTO<PostListDTO>> list(Criteria criteria) throws Exception;
	public Optional<PageDTO<PostListDTO>> myList(MyPostListDTO myPostListDTO) throws Exception;
	public Optional<PageDTO<PostListDTO>> submittedList(SubPostListDTO subPostListDTO) throws Exception;
	public Optional<PostDetailDTO> detail(Long postId) throws Exception;
	public Optional<Long> create(PostCreateDTO postCreateDTO) throws Exception;

}

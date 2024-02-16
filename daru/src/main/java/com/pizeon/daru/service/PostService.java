package com.pizeon.daru.service;

import com.pizeon.daru.dto.cmmn.Criteria;
import com.pizeon.daru.dto.cmmn.PageDTO;
import com.pizeon.daru.dto.post.MyPostCriteriaDTO;
import com.pizeon.daru.dto.post.PostCreateDTO;
import com.pizeon.daru.dto.post.PostDetailDTO;
import com.pizeon.daru.dto.post.PostListDTO;
import com.pizeon.daru.dto.post.SubPostCriteriaDTO;

public interface PostService {
	
	public PageDTO<PostListDTO> list(Criteria criteria);
	public PageDTO<PostListDTO> myList(MyPostCriteriaDTO myPostCriteriaDTO);
	public PageDTO<PostListDTO> submittedList(SubPostCriteriaDTO subPostCriteriaDTO);
	public PostDetailDTO detail(Long postId);
	public Long create(PostCreateDTO postCreateDTO);

}

package com.pizeon.daru.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pizeon.daru.domain.Post;
import com.pizeon.daru.dto.reqDocInfo.ReqDocInfoListResDTO;
import com.pizeon.daru.repository.PostRepository;
import com.pizeon.daru.repository.ReqDocInfoRepository;
import com.pizeon.daru.service.ReqDocService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReqDocServiceImpl implements ReqDocService {
	
	private final PostRepository postRepository;
	private final ReqDocInfoRepository reqDocInfoRepository;

	@Override
	public List<ReqDocInfoListResDTO> infoList(Long postId) throws Exception {
		Post post = postRepository.findById(postId).get();
		
		return reqDocInfoRepository.findByPost(post).stream()
				.map(reqDocInfo -> ReqDocInfoListResDTO.fromEntity(reqDocInfo))
				.toList();
	}

}

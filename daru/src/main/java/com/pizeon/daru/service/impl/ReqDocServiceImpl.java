package com.pizeon.daru.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pizeon.daru.domain.Post;
import com.pizeon.daru.dto.reqDocInfo.ReqDocInfoListDTO;
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
	public List<ReqDocInfoListDTO> infoList(Long postId) {
		Post post = postRepository.findById(postId).get();
		
		return reqDocInfoRepository.findByPost(post).stream()
				.map(reqDocInfo -> ReqDocInfoListDTO.fromEntity(reqDocInfo))
				.toList();
	}

}

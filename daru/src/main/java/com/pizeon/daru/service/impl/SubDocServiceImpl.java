package com.pizeon.daru.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pizeon.daru.domain.Post;
import com.pizeon.daru.domain.SubDoc;
import com.pizeon.daru.domain.SubDocInfo;
import com.pizeon.daru.domain.User;
import com.pizeon.daru.dto.cmmn.Criteria;
import com.pizeon.daru.dto.cmmn.PageDTO;
import com.pizeon.daru.dto.subDoc.SubDocCreateDTO;
import com.pizeon.daru.dto.subDoc.list.SubDocListReqDTO;
import com.pizeon.daru.dto.subDoc.list.SubDocListResDTO;
import com.pizeon.daru.dto.subDocInfo.SubDocInfoCreateDTO;
import com.pizeon.daru.dto.subDocInfo.list.SubDocInfoListReqDTO;
import com.pizeon.daru.dto.subDocInfo.list.SubDocInfoListResDTO;
import com.pizeon.daru.repository.PostRepository;
import com.pizeon.daru.repository.SubDocInfoRepository;
import com.pizeon.daru.repository.SubDocRepository;
import com.pizeon.daru.repository.UserRepository;
import com.pizeon.daru.service.AccessService;
import com.pizeon.daru.service.SubDocService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubDocServiceImpl implements SubDocService {
	
	private final AccessService accessService;
	
	private final PostRepository postRepository;
	private final UserRepository userRepository;
	private final SubDocRepository subDocRepository;
	private final SubDocInfoRepository subDocInfoRepository;

	@Override
	public boolean create(SubDocCreateDTO subDocCreateDTO) {
		Post post = postRepository.findById(subDocCreateDTO.getPostId()).get();
		User user = userRepository.findById(subDocCreateDTO.getUserId()).get();
		
		if (post != null && user != null) {
			SubDoc subDoc = subDocRepository.save(SubDoc.fromCreateDTO(subDocCreateDTO, post, user));
			
			if (subDoc != null) {
				for (SubDocInfoCreateDTO subDocInfoCreateDTO : subDocCreateDTO.getSubDocInfoList()) {
					SubDocInfo subDocInfo =
							subDocInfoRepository.save(SubDocInfo.fromCreateDTO(subDocInfoCreateDTO, subDoc));
					
					if (subDocInfo == null) {
						subDocRepository.delete(subDoc);
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}

	@Override
	public PageDTO<SubDocListResDTO> list(SubDocListReqDTO subDocListReqDTO) {
		try {
			Post post = postRepository.findById(subDocListReqDTO.getSubDocCriteria().getPostId()).get();
			Criteria criteria = subDocListReqDTO.getSubDocCriteria().getCriteria();
			
			Pageable pageable = PageRequest.of(criteria.getPageNum() - 1, criteria.getRowCnt());
			Page<SubDoc> page = subDocRepository.findByPost(post, pageable);
			
			return PageDTO.<SubDocListResDTO>builder()
					.totalPage(page.getTotalPages())
					.pageNum(page.getPageable().getPageNumber() + 1)
					.content(page.get().map(subDoc -> SubDocListResDTO.fromEntity(subDoc)).toList())
					.build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<SubDocInfoListResDTO> infoList(SubDocInfoListReqDTO subDocInfoListReqDTO) {
		try {
			SubDoc subDoc = subDocRepository.findById(subDocInfoListReqDTO.getSubDocId()).get();
			
			if (accessService.checkSubDocAccessPermission(subDoc, subDocInfoListReqDTO.getLoginedId())) {
				return subDoc.getSubDocInfoList().stream()
						.map(subDocInfo -> SubDocInfoListResDTO.fromEntity(subDocInfo))
						.toList();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}

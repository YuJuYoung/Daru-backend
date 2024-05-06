package com.pizeon.daru.service;

import java.util.List;

import com.pizeon.daru.dto.reqDocInfo.ReqDocInfoListResDTO;

public interface ReqDocService {
	
	public List<ReqDocInfoListResDTO> infoList(Long postId) throws Exception;

}

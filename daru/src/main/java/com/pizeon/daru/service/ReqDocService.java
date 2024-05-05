package com.pizeon.daru.service;

import java.util.List;

import com.pizeon.daru.dto.reqDocInfo.ReqDocInfoListDTO;

public interface ReqDocService {
	
	public List<ReqDocInfoListDTO> infoList(Long postId) throws Exception;

}

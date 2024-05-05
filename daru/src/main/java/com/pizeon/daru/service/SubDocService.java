package com.pizeon.daru.service;

import java.util.List;
import java.util.Optional;

import com.pizeon.daru.dto.cmmn.PageDTO;
import com.pizeon.daru.dto.subDoc.SubDocCreateDTO;
import com.pizeon.daru.dto.subDoc.list.SubDocListReqDTO;
import com.pizeon.daru.dto.subDoc.list.SubDocListResDTO;
import com.pizeon.daru.dto.subDocInfo.list.SubDocInfoListReqDTO;
import com.pizeon.daru.dto.subDocInfo.list.SubDocInfoListResDTO;

public interface SubDocService {
	
	public boolean create(SubDocCreateDTO subDocCreateDTO) throws Exception;
	public Optional<PageDTO<SubDocListResDTO>> list(SubDocListReqDTO subDocListReqDTO) throws Exception;
	public Optional<List<SubDocInfoListResDTO>> infoList(SubDocInfoListReqDTO subDocInfoListReqDTO) throws Exception;

}

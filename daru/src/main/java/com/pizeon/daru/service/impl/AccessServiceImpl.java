package com.pizeon.daru.service.impl;

import org.springframework.stereotype.Service;

import com.pizeon.daru.domain.SubDoc;
import com.pizeon.daru.service.AccessService;

@Service
public class AccessServiceImpl implements AccessService {

	@Override
	public boolean checkSubDocAccessPermission(SubDoc subDoc, Long accessId) throws Exception {
		return subDoc.getPost().getWriter().getId().equals(accessId) || subDoc.getUser().getId().equals(accessId);
	}

}

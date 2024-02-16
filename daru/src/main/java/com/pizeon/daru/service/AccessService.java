package com.pizeon.daru.service;

import com.pizeon.daru.domain.SubDoc;

public interface AccessService {
	
	public boolean checkSubDocAccessPermission(SubDoc subDoc, Long accessId);

}

package com.pizeon.daru.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pizeon.daru.domain.Post;
import com.pizeon.daru.domain.ReqDocInfo;
import java.util.List;


public interface ReqDocInfoRepository extends JpaRepository<ReqDocInfo, Long> {
	
	public List<ReqDocInfo> findByPost(Post post);

}

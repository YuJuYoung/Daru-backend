package com.pizeon.daru.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.pizeon.daru.domain.Post;
import com.pizeon.daru.domain.SubDoc;
import com.pizeon.daru.domain.User;

public interface SubDocRepository extends JpaRepository<SubDoc, Long> {
	
	public Page<SubDoc> findByUser(User user, Pageable pageable);
	public Page<SubDoc> findByPost(Post post, Pageable pageable);

}

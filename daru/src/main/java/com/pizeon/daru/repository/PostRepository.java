package com.pizeon.daru.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pizeon.daru.domain.Post;
import com.pizeon.daru.domain.User;

public interface PostRepository extends JpaRepository<Post, Long> {
	
	@Query("SELECT e " +
		   "FROM Post e " +
		   "WHERE (:title IS NULL OR :title = '' OR e.title LIKE %:title%) " +
		   "ORDER BY createdAt DESC")
	public Page<Post> findByTitleContainsOrderByCreatedAtDesc(@Param("title") String keyword, Pageable pageable);
	
	@Query("SELECT e "+
		   "FROM Post e " +
		   "WHERE e.writer = :writer AND (:title IS NULL OR :title = '' OR e.title LIKE %:title%) " +
		   "ORDER BY createdAt DESC")
	public Page<Post> findByTitleContainsAndWriterOrderByCreatedAtDesc(@Param("title") String keyword, @Param("writer") User writer, Pageable pageable);

}

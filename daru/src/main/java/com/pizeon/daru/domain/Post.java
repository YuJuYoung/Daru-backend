package com.pizeon.daru.domain;

import java.time.LocalDateTime;

import com.pizeon.daru.dto.post.PostCreateDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String description;
	
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;
	
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;
	
	@ManyToOne
	@JoinColumn(name = "writer_id")
	private User writer;
	
	public static Post fromCreateDTO(PostCreateDTO postCreateDTO, User writer, LocalDateTime now) {
		return builder()
				.title(postCreateDTO.getTitle())
				.description(postCreateDTO.getDescription())
				.createdAt(now)
				.updatedAt(now)
				.writer(writer)
				.build();
	}
}

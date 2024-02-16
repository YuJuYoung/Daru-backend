package com.pizeon.daru.dto.post;

import java.time.LocalDateTime;

import com.pizeon.daru.domain.Post;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PostDetailDTO {
	
	private String title;
	private String description;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private Long writerId;
	private String writerName;
	
	public static PostDetailDTO fromEntity(Post post) {
		return builder()
				.title(post.getTitle())
				.description(post.getDescription())
				.createdAt(post.getCreatedAt())
				.updatedAt(post.getUpdatedAt())
				.writerId(post.getWriter().getId())
				.writerName(post.getWriter().getName())
				.build();
	}

}

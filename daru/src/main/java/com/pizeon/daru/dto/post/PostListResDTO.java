package com.pizeon.daru.dto.post;

import java.time.format.DateTimeFormatter;

import com.pizeon.daru.domain.Post;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PostListResDTO {
	
	private Long id;
	private String title;
	private String writerName;
	private String createdAt;
	
	public static PostListResDTO fromEntity(Post post) {
		return builder()
				.id(post.getId())
				.title(post.getTitle())
				.writerName(post.getWriter().getName())
				.createdAt(post.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE))
				.build();
	}

}

package com.pizeon.daru.domain;

import java.time.LocalDateTime;
import java.util.List;

import com.pizeon.daru.dto.subDoc.SubDocCreateDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SubDoc {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "post_id")
	private Post post;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;
	
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;
	
	@OneToMany(mappedBy = "subDoc")
	private List<SubDocInfo> subDocInfoList;
	
	public static SubDoc fromCreateDTO(SubDocCreateDTO subDocCreateDTO, Post post, User user) {
		return builder()
				.post(post)
				.user(user)
				.createdAt(subDocCreateDTO.getCreatedAt())
				.updatedAt(subDocCreateDTO.getUpdatedAt())
				.build();
	}

}

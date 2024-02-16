package com.pizeon.daru.domain;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.pizeon.daru.dto.reqDocInfo.ReqDocInfoCreateDTO;

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
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReqDocInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, name = "explain_txt")
	private String explainTxt;
	
	@Column(nullable = false, name = "min_len")
	private Integer minLen;
	
	@Column(nullable = false, name = "max_len")
	private Integer maxLen;
	
	@ManyToOne
	@JoinColumn(name = "post_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Post post;
	
	public static ReqDocInfo fromCreateDTO(ReqDocInfoCreateDTO reqDocInfoCreateDTO, Post post) {
		return builder()
				.explainTxt(reqDocInfoCreateDTO.getExplainTxt())
				.minLen(reqDocInfoCreateDTO.getMinLen())
				.maxLen(reqDocInfoCreateDTO.getMaxLen())
				.post(post)
				.build();
	}

}

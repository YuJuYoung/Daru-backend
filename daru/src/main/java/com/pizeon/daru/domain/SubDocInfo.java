package com.pizeon.daru.domain;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.pizeon.daru.dto.subDocInfo.SubDocInfoCreateDTO;

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
public class SubDocInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "sub_doc_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private SubDoc subDoc;
	
	@Column(name = "explain_txt", nullable = false)
	private String explainTxt;
	
	@Column(nullable = false)
	private String content;
	
	public static SubDocInfo fromCreateDTO(SubDocInfoCreateDTO subDocInfoCreateDTO, SubDoc subDoc) {
		return builder()
				.subDoc(subDoc)
				.explainTxt(subDocInfoCreateDTO.getExplainTxt())
				.content(subDocInfoCreateDTO.getContent())
				.build();
	}

}

package com.pizeon.daru.dto.subDoc.list;

import java.time.format.DateTimeFormatter;

import com.pizeon.daru.domain.SubDoc;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SubDocListResDTO {
	
	private Long id;
	private String writerName;
	private String createdAt;
	
	public static SubDocListResDTO fromEntity(SubDoc subDoc) {
		return builder()
				.id(subDoc.getId())
				.writerName(subDoc.getUser().getName())
				.createdAt(subDoc.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE))
				.build();
	}

}

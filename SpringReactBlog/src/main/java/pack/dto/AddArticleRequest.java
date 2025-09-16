package pack.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pack.domain.Article;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddArticleRequest {
	private String title;
	private String content;
	
	public Article toEntity() {		// 빌더 패턴을 사용해 DTO를 엔티티로 만들어줌
		return Article.builder()
				.title(title)
				.content(content)
				.build();
	}
}

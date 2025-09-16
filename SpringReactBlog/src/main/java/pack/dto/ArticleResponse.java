package pack.dto;

import lombok.Getter;
import pack.domain.Article;

@Getter
///api/articles GET 요청이 오면 글 목록을 조회할 findAllArticles 메서드 작성
public class ArticleResponse {
	private final String title;
	private final String content;
	
	public ArticleResponse(Article article) {
		this.title = article.getTitle();
		this.content = article.getContent();
	}
}

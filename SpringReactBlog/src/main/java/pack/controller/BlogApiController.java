package pack.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import pack.domain.Article;
import pack.dto.AddArticleRequest;
import pack.dto.ArticleResponse;
import pack.dto.UpdateArticleRequest;
import pack.service.BlogService;

@RequiredArgsConstructor
@RestController
// HTTP 응답으로 객체 데이터를 JSON 형식으로 반환
public class BlogApiController {
	private final BlogService blogService;
	
	// HTTP 메서드가 POST일 때, 전달받은 URL과 동일하면 메서드와 매핑
	@PostMapping("/api/articles")
	// @RequestBody로 요청 본문 값 매핑
	public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request){
		Article savedArticle = blogService.save(request);
		// 요청한 자원이 성공적으로 생성되었으며 저장된 블로그 글 정보를 응답 객체 담아 전송
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(savedArticle);
	}
	
	// 전체 글을 조회한 뒤 반환하는 메서드
	@GetMapping("/api/articles")
	public ResponseEntity<List<ArticleResponse>> findAllArticles(){
		List<ArticleResponse> articles = blogService.findAll()
				.stream()
				.map(ArticleResponse::new)
				.toList();
		
		return ResponseEntity.ok()
				.body(articles);
	}
	
	// GET 요청이 오면 블로그 글을 조회
	@GetMapping("/api/articles/{id}")
	// URL 경로에서 값 추출
	public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id){
		Article article = blogService.findById(id);
		
		return ResponseEntity.ok()
				.body(new ArticleResponse(article));
	}
	
	// Delete 요청이 오면 글 삭제
	@DeleteMapping("/api/articles/{id}")
	public ResponseEntity<Void> deleteArticle(@PathVariable long id){
		blogService.delete(id);

		return ResponseEntity.ok()
				.build();
	}
	
	// Put 요청이 오면 글을 수정
	@PutMapping("/api/articles/{id}")
	public ResponseEntity<ArticleResponse> updateArticle(@PathVariable long id, @RequestBody UpdateArticleRequest request){
		Article updatedArticle = blogService.update(id, request);

		return ResponseEntity.ok(new ArticleResponse(updatedArticle));
	}
}

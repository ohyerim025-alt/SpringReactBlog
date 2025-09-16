package pack.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import pack.domain.Article;
import pack.dto.ArticleListViewResponse;
import pack.dto.ArticleViewResponse;
import pack.service.BlogService;

@RequiredArgsConstructor
@Controller
public class BlogViewController {
	private final BlogService blogService;
	
	@GetMapping("/articles")
	public String getArticles(Model model) {
		List<ArticleListViewResponse> articles = blogService.findAll().stream()
				.map(ArticleListViewResponse::new)
				.toList();
		model.addAttribute("articles", articles);
		
		return "articleList";
	}
	
	// 인자 id에 url로 넘어온 값을 받아 findById()메서드로 넘겨 글을 조회, 화면에 사용할 모델에 데이터 저장, 보여줄화면의 템플릿 이름반환
	@GetMapping("/articles/{id}")
	public String getArticle(@PathVariable Long id, Model model) {
		Article article = blogService.findById(id);
		model.addAttribute("article", new ArticleViewResponse(article));
		return "article";
	}
	
	// 수정화면을 보여주기 위한 메서드
	@GetMapping("/new-article")
	public String newArticle(@RequestParam(required = false) Long id, Model model) {
		if(id == null) {
			model.addAttribute("article", new ArticleViewResponse());
		} else {
			Article article = blogService.findById(id);
			model.addAttribute("article", new ArticleViewResponse(article));
		}
		return "newArticle";
	}
}

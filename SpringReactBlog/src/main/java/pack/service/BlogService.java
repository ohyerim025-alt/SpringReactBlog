package pack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import pack.domain.Article;
import pack.dto.AddArticleRequest;
import pack.dto.UpdateArticleRequest;
import pack.repository.BlogRepository;

@RequiredArgsConstructor
@Service
public class BlogService {
	@Autowired
	BlogRepository blogRepository;
	
	// 블로그 글 추가 메서드
	public Article save(AddArticleRequest request) {
		return blogRepository.save(request.toEntity());
	}
	
	// 데이터베이스에 저장되어 있는 글을 모두 가져오는 메서드
	public List<Article> findAll(){
		return blogRepository.findAll();
	}
	
	// 데이터 베이스에 저장되어 있는 글의 ID를 통해 글 조회
	public Article findById(long id) {
		return blogRepository.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("not found: " + id));
	}
	
	// 블로그 글의 id를 받은 뒤 deleteById()메서드를 이용해 데이터베이스 데이터 삭제
	public void delete(long id) {
		blogRepository.deleteById(id);
	}
	
	// 리포지터리를 사용해 글을 수정하는 메서드
	@Transactional // 트랜잭션 메서드
	public Article update(long id, UpdateArticleRequest request) {
		Article article = blogRepository.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("Not found: "+ id));
		
		article.update(request.getTitle(), request.getContent());
		
		return article;
	}
}

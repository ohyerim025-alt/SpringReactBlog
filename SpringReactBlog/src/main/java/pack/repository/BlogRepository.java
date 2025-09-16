package pack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pack.domain.Article;

// JpaRepository를 상속받아 JpaRepository가 제공한느 여러 메서드 사용 가능
public interface BlogRepository extends JpaRepository<Article, Long> {
	
}
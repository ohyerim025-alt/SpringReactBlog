package pack.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@EntityListeners(AuditingEntityListener.class)
@Entity  // 엔티티로 지정
//@Table(name = "article") 
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {
    @Id		// id 필드를 기본키로 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 기본키를 자동으로 1씩 증가
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", nullable = false)	// 'title'이라는 not null 컬럼과 매핑
    private String title;

    @Column(name = "content", nullable = false)
    private String content;
    
    @CreatedDate
    @Column(name= "created_at")
    private LocalDateTime createdAt;
    
    @LastModifiedDate
    @Column(name="updated_at")
    private LocalDateTime updatedAt;
    
    /*
     * 빌더 패턴
     * 빌더 패턴을 사용하지 않을 시: 데이터 값이 어느 필드에 들어가는 값인지 파악하기 힘듦
     * 빌더 패턴을 사용하면 어느 필드에 값이 매칭되는지 바로 보이기 때문에 가독성이 높아짐
     */
    @Builder		// 빌더 패턴으로 객체 생성
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
    
    
}
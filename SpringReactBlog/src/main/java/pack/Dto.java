package pack;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pack.domain.Article;

@NoArgsConstructor
@AllArgsConstructor
@Getter

public class Dto {
    private String title;
    private String content;
    
    public Article toEntity() {
        return Article.builder()
                .title(title)
                .content(content)
                .build();
        // return new Article(title, content);  위의 return 문과 동일하다.
    }
}

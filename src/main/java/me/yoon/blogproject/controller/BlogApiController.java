package me.yoon.blogproject.controller;

import lombok.RequiredArgsConstructor;
import me.yoon.blogproject.domain.Article;
import me.yoon.blogproject.dto.AddArticleRequest;
import me.yoon.blogproject.dto.ArticleResponse;
import me.yoon.blogproject.dto.UpdateArticleRequest;
import me.yoon.blogproject.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RestController // HTTP Response Body 객체 데이터를 JSON 형식으로 반환하는 컨트롤러
public class BlogApiController {
    private final BlogService blogService;

    //HTTP 메서드가 POST일 때 전달받은 URL와 동일하면 메서드로 매핑
    @PostMapping("/api/articles")
    //요청 본문 값 매핑
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request, Principal principal){
        Article savedArticle = blogService.save(request, principal.getName());
        //요청한 자원이 성공적으로 생성되었으며  저장된 블로그 글 정보를 응답 객체에 담아 전송
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }

    @GetMapping("/api/articles")  //GET요청
    public ResponseEntity<List<ArticleResponse>> findAllarticles(){
        List<ArticleResponse> articles = blogService.findAll() //글 전체 조회 메서드 호출
                .stream()
                .map(ArticleResponse::new) //응답용 객체인 Ar~로 파싱해
                .toList();

        return ResponseEntity.ok()
                .body(articles); //바디에 담아 클라이언트에 전송
    }

    @GetMapping("/api/articles/{id}")
    //URL 경로에서 값 추출
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id){
        Article article = blogService.findById(id);

        return ResponseEntity.ok()
                .body(new ArticleResponse(article));
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable long id){
        blogService.delete(id);
        return ResponseEntity.ok()
                .build();
    }

    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable long id,
                                                 @RequestBody UpdateArticleRequest request){
        Article updateArticle = blogService.update(id, request);

        return ResponseEntity.ok()
                .body(updateArticle);
    }


}

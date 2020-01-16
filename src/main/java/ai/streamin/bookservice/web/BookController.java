package ai.streamin.bookservice.web;

import ai.streamin.bookservice.book.GoogleBookResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@RestController
public class BookController {

  @Value("${google.books.api.isbn.query}")
  private String googleISBNQuery;

  @Value("${google.books.api.author.query}")
  private String googleAuthorQuery;

  @Value("${google.books.api.title.query}")
  private String googleTitleQuery;

  private final RestTemplate restTemplate;

  public BookController(RestTemplateBuilder restTemplateBuilder) {
    this.restTemplate = restTemplateBuilder
      .setConnectTimeout(Duration.ofSeconds(10))
      .setReadTimeout(Duration.ofSeconds(10))
      .build();
  }

  @GetMapping("/search/isbn")
  @ResponseBody
  public ResponseEntity<GoogleBookResult> getByISBN(@RequestParam(name="isbn", required=true) String isbn) {
    String url = googleISBNQuery + isbn;
    ResponseEntity<GoogleBookResult> result = this.restTemplate.getForEntity(url, GoogleBookResult.class);
    return ResponseEntity.status(result.getStatusCode()).body(result.getBody());
  }

  @GetMapping("/search/author")
  @ResponseBody
  public ResponseEntity<GoogleBookResult> getByAuthor(@RequestParam(name="author", required=true) String author) {
    String url = googleAuthorQuery + author;
    ResponseEntity<GoogleBookResult> result = this.restTemplate.getForEntity(url, GoogleBookResult.class);
    return ResponseEntity.status(result.getStatusCode()).body(result.getBody());
  }

  @GetMapping("/search/title")
  @ResponseBody
  public ResponseEntity<GoogleBookResult> getByTitle(@RequestParam(name="title", required=true) String title) {
    String url = googleTitleQuery + title;
    ResponseEntity<GoogleBookResult> result = this.restTemplate.getForEntity(url, GoogleBookResult.class);
    return ResponseEntity.status(result.getStatusCode()).body(result.getBody());
  }

}

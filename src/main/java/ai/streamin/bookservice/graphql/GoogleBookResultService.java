package ai.streamin.bookservice.graphql;

import ai.streamin.bookservice.book.BookInfo;
import ai.streamin.bookservice.book.GoogleBookResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Service
@Slf4j
public class GoogleBookResultService {

  @Value("${google.books.api.isbn.query}")
  private String googleISBNQuery;

  @Value("${google.books.api.author.query}")
  private String googleAuthorQuery;

  @Value("${google.books.api.title.query}")
  private String googleTitleQuery;

  private final RestTemplate restTemplate;

  public GoogleBookResultService(RestTemplateBuilder restTemplateBuilder) {
    this.restTemplate = restTemplateBuilder
      .setConnectTimeout(Duration.ofSeconds(10))
      .setReadTimeout(Duration.ofSeconds(10))
      .build();
  }

  public GoogleBookResult getGoogleBookResultByISBN(String isbn) {
    String url = googleISBNQuery + isbn;
    ResponseEntity<GoogleBookResult> result = this.restTemplate.getForEntity(url, GoogleBookResult.class);
    return result.getBody();
  }

  public GoogleBookResult getGoogleBookResultByAuthor(String author) {
    String url = googleAuthorQuery + author;
    ResponseEntity<GoogleBookResult> result = this.restTemplate.getForEntity(url, GoogleBookResult.class);
    return result.getBody();
  }

  public GoogleBookResult getGoogleBookResultByTitle(String title) {
    String url = googleTitleQuery + title;
    ResponseEntity<GoogleBookResult> result = this.restTemplate.getForEntity(url, GoogleBookResult.class);
    return result.getBody();
  }

}

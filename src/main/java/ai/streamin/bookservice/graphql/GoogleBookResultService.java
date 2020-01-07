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
  private String googleQuery;

  private final RestTemplate restTemplate;

  public GoogleBookResultService(RestTemplateBuilder restTemplateBuilder) {
    this.restTemplate = restTemplateBuilder
      .setConnectTimeout(Duration.ofSeconds(10))
      .setReadTimeout(Duration.ofSeconds(10))
      .build();
  }

  public GoogleBookResult getGoogleBookResultByISBN(String isbn) {
    String url = googleQuery + isbn;
    ResponseEntity<GoogleBookResult> response = this.restTemplate.getForEntity(url, GoogleBookResult.class);
    if (response.getStatusCode() == HttpStatus.OK) {
      return response.getBody();
    } else {
      return null;
    }
  }

}

package ai.streamin.bookservice.web;

import ai.streamin.bookservice.book.Book;
import ai.streamin.bookservice.book.GoogleBookResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
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
  private String googleQuery;

  private final RestTemplate restTemplate;

  public BookController(RestTemplateBuilder restTemplateBuilder) {
    this.restTemplate = restTemplateBuilder
      .setConnectTimeout(Duration.ofSeconds(10))
      .setReadTimeout(Duration.ofSeconds(10))
      .build();
  }

  @GetMapping("/isbn")
  @ResponseBody
  public Book getByISBN(@RequestParam(name="isbn", required=true) String isbn) {
    String url = googleQuery + isbn;
    ResponseEntity<GoogleBookResult> response = this.restTemplate.getForEntity(url, GoogleBookResult.class);
    if (response.getStatusCode() == HttpStatus.OK) {
      GoogleBookResult result = response.getBody();
      return new Book(result.getItems().get(0).getVolumeInfo().getTitle(),
        result.getItems().get(0).getVolumeInfo().getAuthors().get(0),
        result.getItems().get(0).getVolumeInfo().getImageLinks().getSmallThumbnail(),
        isbn);
    } else {
      return null;
    }
  }

}

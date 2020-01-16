package ai.streamin.bookservice.book;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {
  private String name;
  private String author;
  private String pictureURL;
  private String isbn;
}

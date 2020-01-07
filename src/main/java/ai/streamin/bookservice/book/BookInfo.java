package ai.streamin.bookservice.book;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class BookInfo implements Serializable {

  private String title;
  private List<String> authors = new ArrayList();
  private String publisher;
  private List<IndustryIdentifier> industryIdentifiers = new ArrayList();
  int pageCount;
  private double averageRating;
  private ImageLink imageLinks;
  private String language;

}

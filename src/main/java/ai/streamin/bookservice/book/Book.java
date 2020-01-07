package ai.streamin.bookservice.book;

import lombok.Data;

import java.io.Serializable;

@Data
public class Book implements Serializable {

  private BookInfo volumeInfo;

}

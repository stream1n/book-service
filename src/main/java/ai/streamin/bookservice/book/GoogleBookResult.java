package ai.streamin.bookservice.book;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class GoogleBookResult implements Serializable {

  int totalItems;
  List<Book> items = new ArrayList();

}


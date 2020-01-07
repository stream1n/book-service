package ai.streamin.bookservice.book;

import lombok.Data;

import java.io.Serializable;

@Data
public class IndustryIdentifier implements Serializable {
  private String type;
  private String identifier;
}

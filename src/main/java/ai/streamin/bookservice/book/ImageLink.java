package ai.streamin.bookservice.book;

import lombok.Data;

import java.io.Serializable;

@Data
public class ImageLink implements Serializable {

  private String smallThumbnail;
  private String thumbnail;

}

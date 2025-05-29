package peaksoft.models;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Post {
  private Long id;
  private String image;
  private String description;
  private LocalDate created_date;

  public Post(String image, String description, LocalDate created_date) {
    this.image = image;
    this.description = description;
    this.created_date = created_date;
  }
}

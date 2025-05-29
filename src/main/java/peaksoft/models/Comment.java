package peaksoft.models;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Comment {
    private Long id;
    private String text;
    private Long userId;
    private Long postId;

    public Comment(String text,Long userId, Long postId) {
        this.text = text;
        this.userId = userId;
        this.postId = postId;
    }
}

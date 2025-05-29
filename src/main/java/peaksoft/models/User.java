package peaksoft.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private Long id;
    private String userName;
    private String email;
    private String password;

    public User(String userName, String email, String password) {
        this. userName = userName;
        this.email = email;
        this.password = password;
    }
}

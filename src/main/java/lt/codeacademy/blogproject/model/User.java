package lt.codeacademy.blogproject.model;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "VARCHAR(36)" , updatable = false)
    @Type(type = "uuid-char")
    private UUID userID;
    private String username;
    private String password;
    private String role;
}

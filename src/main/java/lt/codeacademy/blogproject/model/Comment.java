package lt.codeacademy.blogproject.model;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "Comments")
public class Comment {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "VARCHAR(36)", updatable = false)
    @Type(type = "uuid-char")
    private UUID commentID;
    private String description;
    private UUID userID;
    private UUID blogID;
}

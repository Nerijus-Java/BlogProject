package lt.codeacademy.blogproject.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "Comments")
public class Comment {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "VARCHAR(36)", updatable = false)
    @Type(type = "uuid-char")
    private UUID commentID;

    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "commentIdBlog")
    private Blog blog;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "commentIdUser")
    private User user;


}

package lt.codeacademy.blogproject.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "Blogs")
public class Blog {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "VARCHAR(36)", updatable = false)
    @Type(type = "uuid-char")
    private UUID blogID;

    private String title;

    private String description;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "commentIdBlog")
    private Set<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "blogIdUser")
    private User user;

}

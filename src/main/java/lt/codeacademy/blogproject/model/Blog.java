package lt.codeacademy.blogproject.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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

    @Size(min = 3, max = 255)
    private String title;

    @Size(min = 10, max = 255)
    private String description;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "commentIdBlog")
    private Set<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "blogIdUser")
    private User user;

}

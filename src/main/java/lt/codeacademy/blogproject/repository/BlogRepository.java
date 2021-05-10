package lt.codeacademy.blogproject.repository;

import lt.codeacademy.blogproject.model.Blog;
import lt.codeacademy.blogproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BlogRepository extends JpaRepository<Blog, UUID> {

    Blog findByTitle(String title);

    List<Blog> findByUser(User user);

}

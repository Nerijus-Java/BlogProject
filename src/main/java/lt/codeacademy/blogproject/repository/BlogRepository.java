package lt.codeacademy.blogproject.repository;

import lt.codeacademy.blogproject.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BlogRepository extends JpaRepository<Blog, UUID> {
}

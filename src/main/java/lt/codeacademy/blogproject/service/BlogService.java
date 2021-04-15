package lt.codeacademy.blogproject.service;

import lt.codeacademy.blogproject.model.Blog;

import java.util.List;
import java.util.UUID;

public interface BlogService {

    void addBlog(Blog blog);

    Blog getBlog(UUID id);

    List<Blog> getAllBlogs();

    void updateBlog(Blog blog);

    void removeBlog(UUID id);

    Blog getByTitle(String title);
}

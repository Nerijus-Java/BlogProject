package lt.codeacademy.blogproject.service;

import lt.codeacademy.blogproject.model.Blog;
import lt.codeacademy.blogproject.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BlogServiceMpl implements BlogService{

    private final BlogRepository blogRepository;

    public BlogServiceMpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public void addBlog(Blog blog) {

    }

    @Override
    public void getBlog(UUID id) {

    }

    @Override
    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    @Override
    public void updateBlog(Blog blog) {

    }

    @Override
    public void removeBlog(UUID id) {

    }

    @Override
    public Blog getByTitle(String title) {
        return null;
    }
}

package lt.codeacademy.blogproject.service;

import lt.codeacademy.blogproject.model.Blog;
import lt.codeacademy.blogproject.model.User;
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
        if (blog == null){
            return;
        }
        blogRepository.save(blog);
    }

    @Override
    public Blog getBlog(UUID id) {
        return blogRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    @Override
    public void updateBlog(Blog blog) {
        blogRepository.save(blog);
    }

    @Override
    public void removeBlog(UUID id) {
        blogRepository.deleteById(id);
    }

    @Override
    public Blog getByTitle(String title) {
        return blogRepository.findByTitle(title);
    }

    public List<Blog> getByUser(User user){
        return blogRepository.findByUser(user);
    }
}

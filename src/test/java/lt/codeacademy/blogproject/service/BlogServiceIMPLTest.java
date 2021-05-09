package lt.codeacademy.blogproject.service;

import lt.codeacademy.blogproject.model.Blog;
import lt.codeacademy.blogproject.repository.BlogRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BlogServiceIMPLTest {

    @Mock
    private BlogRepository blogRepository;

    @Mock
    private Blog blog;

    @InjectMocks
    private BlogServiceIMPL blogServiceIMPL;

    @Test
    public void testGetBlogsWhenBlogsDontExist() {
        when(blogRepository.findAll()).thenReturn(Collections.emptyList());

        List<Blog> blogs = blogRepository.findAll();

        assertNotNull(blogs);
        assertTrue(blogs.isEmpty());
    }

    @Test
    public void testGetBlogsWhenBlogsExist() {
        when(blogRepository.findAll()).thenReturn(List.of(blog));

        List<Blog> blogs = blogRepository.findAll();

        assertNotNull(blogs);
        assertFalse(blogs.isEmpty());
    }

    @Test
    public void testAddBlog(){
        when(blogRepository.save(blog)).thenReturn(blog);

        blogServiceIMPL.addBlog(blog);

        verify(blogRepository, times(1)).save(blog);
    }

    @Test
    public void testAddBlogWhenBlogDontExist(){
        blogServiceIMPL.addBlog(null);

        verify(blogRepository, times(0)).save(eq(null));
    }
}
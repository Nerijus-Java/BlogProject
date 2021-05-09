package lt.codeacademy.blogproject.service;

import lt.codeacademy.blogproject.model.Comment;
import lt.codeacademy.blogproject.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CommentServiceIMPL implements CommentService{
    private final CommentRepository commentRepository;

    public CommentServiceIMPL(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public void addComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public Comment getComment(UUID id) {
        return commentRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public void updateComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public void removeComment(UUID id) {
        commentRepository.deleteById(id);
    }
}

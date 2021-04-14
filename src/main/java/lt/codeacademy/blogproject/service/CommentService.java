package lt.codeacademy.blogproject.service;

import lt.codeacademy.blogproject.model.Comment;

import java.util.List;
import java.util.UUID;

public interface CommentService {

    void addComment(Comment comment);

    void getComment(UUID id);

    List<Comment> getAllComments();

    void updateComment(Comment comment);

    void removeComment(UUID id);

}

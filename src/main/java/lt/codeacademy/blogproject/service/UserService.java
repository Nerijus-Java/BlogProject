package lt.codeacademy.blogproject.service;


import lt.codeacademy.blogproject.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    void addUser(User user);

    User getUser(UUID id);

    List<User> getAllUsers();

    void updateBlog(User user);

    void removeBlog(UUID id);

    User getByUsername(String username);
}

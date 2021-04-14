package lt.codeacademy.blogproject.service;

import lt.codeacademy.blogproject.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceMpl implements UserService{
    @Override
    public void addUser(User user) {

    }

    @Override
    public void getUser(UUID id) {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void updateBlog(User user) {

    }

    @Override
    public void removeBlog(UUID id) {

    }

    @Override
    public User getByUsername(String username) {
        return null;
    }
}

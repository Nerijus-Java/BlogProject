package lt.codeacademy.blogproject.service;

import lt.codeacademy.blogproject.model.User;
import lt.codeacademy.blogproject.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceMpl implements UserService{
    private final UserRepository userRepository;

    public UserServiceMpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUser(UUID id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void updateBlog(User user) {
        userRepository.save(user);
    }

    @Override
    public void removeBlog(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}

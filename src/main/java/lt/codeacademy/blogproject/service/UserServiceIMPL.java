package lt.codeacademy.blogproject.service;

import lt.codeacademy.blogproject.model.User;
import lt.codeacademy.blogproject.repository.UserRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Primary
public class UserServiceIMPL implements UserDetailsService {
    private final UserRepository userRepository;

    public UserServiceIMPL(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByNameWithRows(s).orElseThrow(() -> new UsernameNotFoundException(s + " dose not exist"));
    }

    public User getUserById(UUID id){
        return userRepository.getOne(id);
    }

    public void saveUser(User user){
        userRepository.save(user);
    }
}

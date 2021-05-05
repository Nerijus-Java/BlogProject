package lt.codeacademy.blogproject.service;

import lombok.extern.slf4j.Slf4j;
import lt.codeacademy.blogproject.model.User;
import lt.codeacademy.blogproject.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceMpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserServiceMpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByNameWithRows(s).orElseThrow(() -> new UsernameNotFoundException(s + " dose not exist"));
    }

    public void saveUser(User user){
        userRepository.save(user);
    }
}

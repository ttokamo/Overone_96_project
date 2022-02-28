package by.overone.it.service;

import by.overone.it.encoder.PasswordEncoder;
import by.overone.it.entity.User;
import by.overone.it.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private void save(User user) {
        userRepository.save(user);
    }

    public void save(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(PasswordEncoder.encodePassword(password));
        save(user);
    }

    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }
}

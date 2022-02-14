package by.overone.it.service;

import by.overone.it.entity.User;
import by.overone.it.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private void save(User user) {
        userRepository.save(user);
    }
}

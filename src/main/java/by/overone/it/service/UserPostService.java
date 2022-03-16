package by.overone.it.service;

import by.overone.it.entity.UserPost;
import by.overone.it.repository.UserPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserPostService {

    @Autowired
    private UserPostRepository userRepository;

    public void save(String text, String ownerId, String ownerNickname) {
        UserPost userPost = new UserPost();
        userPost.setOwnerId(ownerId);
        userPost.setOwnerNickname(ownerNickname);
        userPost.setText(text);
        userPost.setPostTime(LocalDateTime.now());
    }

    private void save(UserPost post) {
        userRepository.save(post);
    }

    public List<UserPost> getMessagesList() {
        return userRepository.findAll();
    }
}

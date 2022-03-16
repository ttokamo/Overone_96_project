package by.overone.it.repository;

import by.overone.it.entity.UserPost;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserPostRepository extends JpaRepository<UserPost, String> {

}

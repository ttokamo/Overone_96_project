package by.overone.it.repository;

import by.overone.it.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    @Query("from User where username =:username")
    Optional<User> findUserByUsername(@Param("username") String username);
}

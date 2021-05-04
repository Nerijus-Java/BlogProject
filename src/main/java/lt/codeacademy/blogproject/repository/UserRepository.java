package lt.codeacademy.blogproject.repository;

import lt.codeacademy.blogproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("SELECT U FROM User U join fetch U.roles where U.username = :name")
    Optional<User> findByNameWithRows(@Param("name") String name);
}

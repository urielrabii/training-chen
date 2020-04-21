package team1.spring.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team1.spring.training.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
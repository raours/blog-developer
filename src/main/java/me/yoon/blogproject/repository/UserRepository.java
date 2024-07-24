package me.yoon.blogproject.repository;

import me.yoon.blogproject.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); //email로 사용자정보를 가져옴
}

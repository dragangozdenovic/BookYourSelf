package com.example.goBookYourself.repository;

import com.example.goBookYourself.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameIgnoreCase(String username);
    User findByUsernameAndEnabled( String username, boolean enabled );
    User findById(Integer id);
    void deleteByUsernameIgnoreCase(String username);
    User findByEmail(String email);
}

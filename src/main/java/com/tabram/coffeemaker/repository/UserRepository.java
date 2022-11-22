package com.tabram.coffeemaker.repository;

import com.tabram.coffeemaker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    void deleteUserById(Long id);
    User findByUsername(String username);

    Optional<User> findById(Long id);

    boolean existsByUsername(String username);
}

package com.blckRbbit.hypnoSeBlog.repository;

import com.blckRbbit.hypnoSeBlog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

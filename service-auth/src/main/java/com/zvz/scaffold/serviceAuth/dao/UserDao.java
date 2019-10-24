package com.zvz.scaffold.serviceAuth.dao;

import com.zvz.scaffold.serviceAuth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

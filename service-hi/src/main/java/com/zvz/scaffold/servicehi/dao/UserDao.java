package com.zvz.scaffold.servicehi.dao;


import com.zvz.scaffold.servicehi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

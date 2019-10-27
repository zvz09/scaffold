package com.zvz.scaffold.serviceUser.dao;


import com.zvz.scaffold.serviceUser.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

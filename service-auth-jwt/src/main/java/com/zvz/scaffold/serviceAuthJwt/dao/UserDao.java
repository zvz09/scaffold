package com.zvz.scaffold.serviceAuthJwt.dao;

import com.zvz.scaffold.serviceAuthJwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

    User findByUsername(String username);
}

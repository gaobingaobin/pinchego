package com.bxg.pinchego.repository;

import com.bxg.pinchego.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author gaobin
 * @createDate ${Date}
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsernameAndPassword(String username,String password);
    User findByUsername(String username);
}

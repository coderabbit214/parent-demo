package com.jsh.dao;

import com.jsh.entity.Classes;
import com.jsh.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author mac
 */
public interface UserRepository extends JpaRepository<User, Integer> {

}
package com.coderabbit.security.dao;


import com.coderabbit.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsernameAndStatus(String username,Integer status);
}

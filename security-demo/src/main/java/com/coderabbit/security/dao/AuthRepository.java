package com.coderabbit.security.dao;


import com.coderabbit.security.model.AuthInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<AuthInfo,Long> {
    AuthInfo findByUrlAndStatus(String url, Integer status);
}
